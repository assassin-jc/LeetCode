package com.algorithm.leetcode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * *****************************************************************************
 * LeetCode 数组部分算法题
 *
 * @date 2018/4/24 10:21
 * @Author: <a href=mailto:jiangchen@bonree.com>姜宸</a>;
 * @Package com.algorithm.leetcode.array
 * Description: ${DESCRIPTION}
 * Version: 1.0
 * ****************************************************************************
 */
public class ArraySolution {

    public static void main(String[] args) {
        // 调试代码
//        removeDuplicates(new int[]{7, 1, 5, 7, 6, 4});
//        maxProfit(new int[]{7, 2, 6, 3, 7, 4});
//        rotate(new int[]{1, 2}, 3);
//        singleNumber(new int[]{1, 0, 1});
//        plusOne(new int[]{1, 2, 3});
//        twoSum(new int[]{-1, -2, -3, -4, -5}, -8);
//        moveZeroes(new int[]{0, 1, 0, 3, 12});
//        intersect(new int[]{2, 1}, new int[]{1, 2});
        System.out.println(sum(new int[]{1, 2, 4, 6, 7, 8, 9}));

    }

    /**
     * 从排序数组中删除重复项
     * <p>
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[num]) {
                num++;
                nums[num] = nums[i];
            }
        }
        return num + 1;
    }


    /**
     * 买卖股票的最佳时机 II
     * <p>
     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int buy = prices[0];
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] > buy) {
                profit += prices[i] - buy;
                buy = prices[i];
            } else {
                buy = prices[i];
            }
        }
        return profit;
    }

    /**
     * 旋转数组
     * <p>
     * 将包含 n 个元素的数组向右旋转 k 步。
     * 例如，如果  n = 7 ,  k = 3，给定数组  [1,2,3,4,5,6,7]  ，向右旋转后的结果为 [5,6,7,1,2,3,4]。
     *
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        int count = 0;
        if (k >= nums.length) {
            count = k % nums.length;
        } else {
            count = k;
        }
        if (nums.length == 0 || count == 0) {
            return;
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i < count) {
                result[i] = nums[i + nums.length - count];
            } else {
                result[i] = nums[i - count];
            }
        }
        System.arraycopy(result, 0, nums, 0, nums.length);
    }

    /**
     * 存在重复
     * <p>
     * 给定一个整数数组，判断是否存在重复元素。
     * 如果任何值在数组中出现至少两次，函数应该返回 true。如果每个元素都不相同，则返回 false。
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        if (nums.length == 0) {
            return false;
        }
        Set<Integer> numbers = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (numbers.contains(nums[i])) {
                return true;
            } else {
                numbers.add(nums[i]);
            }
        }
        return false;
    }

    /**
     * 只出现一次的数字
     * <p>
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    break;
                }
            }
            result = nums[i];
        }
        return result;
    }

    /**
     * 两个数组的交集 II
     * <p>
     * 给定两个数组，写一个方法来计算它们的交集。
     *
     * @param nums1
     * @param nums2
     * @return
     */

    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] tmp = new int[nums1.length < nums2.length ? nums1.length : nums2.length];
        int i = 0, j = 0, k = 0;

        while (i < nums1.length && j < nums2.length) {
            int n1 = nums1[i], n2 = nums2[j];
            if (n1 == n2) {
                tmp[k++] = n1;
            }
            if (n1 <= n2) {
                i++;
            }
            if (n1 >= n2) {
                j++;
            }
        }
        return Arrays.copyOf(tmp, k);
    }

    /**
     * 加一
     * <p>
     * 给定一个非负整数组成的非空数组，在该数的基础上加一，返回一个新的数组。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     *
     * @param digits
     * @return
     */

    public static int[] plusOne(int[] digits) {

        if (digits == null || digits.length == 0) {
            return digits;
        }
        boolean carry = false;
        int[] result = new int[digits.length];
        for (int i = digits.length - 1; i >= 0; i--) {
            if ((carry || i == (digits.length - 1))) {
                if (digits[i] == 9) {
                    result[i] = 0;
                    carry = true;
                } else {
                    result[i] = digits[i] + 1;
                    carry = false;
                }
            } else {
                result[i] = digits[i];
            }
        }
        if (carry) {
            result = new int[digits.length + 1];
            result[0] = 1;
        }

        return result;
    }

    /**
     * 移动零
     * <p>
     * 给定一个数组 nums, 编写一个函数将所有 0 移动到它的末尾，同时保持非零元素的相对顺序。
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int pos = 0;
        // 将非0数字都尽可能向前排
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[pos] = nums[i];
                pos++;
            }
        }
        // 将剩余的都置0
        for (; pos < nums.length; pos++) {
            nums[pos] = 0;
        }
    }

    /**
     * 两数之和
     * <p>
     * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
     * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        for (int i = 0; i < nums.length; i++) {
            int tmp = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (tmp == nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * 算法图解练习4.1
     * <p>
     *     数组求和的递归形式
     *
     * @param nums
     * @return
     */
    public static int sum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else {
            int[] numArr = new int[nums.length - 1];
            System.arraycopy(nums, 1, numArr, 0, nums.length - 1);
            return nums[0] + sum(numArr);
        }
    }


}
