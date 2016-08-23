package array;

/**
 * 4. Median of Two Sorted Arrays
 * 
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * 
 * Find the median of the two sorted arrays. The overall run time complexity
 * should be O(log (m+n)).
 * 
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 
 * The median is 2.0
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 
 * The median is (2 + 3)/2 = 2.5
 * 
 * @see https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 */
public class FindMedianSortedArrays {
	
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	if (nums1.length < nums2.length) return findMedianSortedArrays(nums2, nums1);
        int n = nums1.length, m = nums2.length, N = n + m;
        if (m == 0) return (nums1[(n-1)/2] + nums1[n/2]) / 2.0;
        int lo = 0, hi = 2 * m;
        while (true) {
        	int cut2 = lo + (hi - lo) / 2;
        	int cut1 = N - cut2;
        	int L1 = (cut1 == 0) ? Integer.MIN_VALUE : nums1[(cut1-1)/2];
        	int L2 = (cut2 == 0) ? Integer.MIN_VALUE : nums2[(cut2-1)/2];
        	int R1 = (cut1 == 2*n) ? Integer.MAX_VALUE : nums1[cut1/2];
        	int R2 = (cut2 == 2*m) ? Integer.MAX_VALUE : nums2[cut2/2];
        	if (L1 > R2) lo = cut2 + 1;
        	else if (L2 > R1) hi = cut2 - 1;
        	else return (Math.max(L1, L2) + Math.min(R1, R2)) / 2.0;
        }
    }
    
    // ------------------------------
    private static FindMedianSortedArrays findMedian = new FindMedianSortedArrays();
    
    public static void main(String[] args) {
		System.out.println(findMedian.findMedianSortedArrays(new int[]{1,2,3}, new int[]{})); 	// 2.0
		System.out.println(findMedian.findMedianSortedArrays(new int[]{}, new int[]{1,2,3,4})); // 2.5
		System.out.println(findMedian.findMedianSortedArrays(new int[]{1,3}, new int[]{2}));	// 2.0
		System.out.println(findMedian.findMedianSortedArrays(new int[]{1,2}, new int[]{3}));	// 2.0
		System.out.println(findMedian.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));	// 2.5
		System.out.println(findMedian.findMedianSortedArrays(new int[]{1,1}, new int[]{1,2}));	// 1.0
	}
    
}

/**
 * similar to merge sort.
 */
class OnTime extends FindMedianSortedArrays {
	
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int n = nums1.length, m = nums2.length, total = n + m;
		int idx1 = 0, idx2 = 0, end = total / 2 + 1;
		int pre = 0, cur = 0;
		for (int i = 0; i < end; i++) {
			pre = cur;
			if ((idx2 == m || (idx1 < n && nums1[idx1] < nums2[idx2])))
				cur = nums1[idx1++];
			else
				cur = nums2[idx2++];
		}
		return (total % 2 != 0) ? cur : (pre + cur) / 2.0;
	}
    
}
