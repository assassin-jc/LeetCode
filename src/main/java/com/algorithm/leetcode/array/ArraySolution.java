package com.algorithm.leetcode.array;

import java.util.*;

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
    public static ListNode l1 = new ListNode(1);
    public static ListNode l2 = new ListNode(2);

    static {

        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        ListNode six = new ListNode(6);

        l1.next = two;
        two.next = three;
        l2.next = four;
        four.next = five;
        five.next = six;
    }

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
//        System.out.println(sum(new int[]{1, 2, 4, 6, 7, 8, 9}));
//        System.out.println(quickSort(new ArrayList<>(Arrays.asList(new Integer[]{2, 1, 4, 5, 6, 9, 8}))));

        //recursionMergeTwoLists(l1, l2);
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
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * <p>
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     * <p>
     * 注意你不能在买入股票前卖出股票。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     * 示例 2:
     * <p>
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
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
     * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：[-4,-1,0,3,10]
     * 输出：[0,1,9,16,100]
     * 示例 2：
     * <p>
     * 输入：[-7,-3,2,3,11]
     * 输出：[4,9,9,49,121]
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= A.length <= 10000
     * -10000 <= A[i] <= 10000
     * A 已按非递减顺序排序。
     *
     * @param A
     * @return
     */
    public int[] sortedSquares(int[] A) {
        int N = A.length;
        int j = 0;
        while (j < N && A[j] < 0)
            j++;
        int i = j - 1;
        int k = 0;
        int[] result = new int[N];
        while (i >= 0 && j < N) {
            if (A[i] * A[i] < A[j] * A[j]) {
                result[k++] = A[i] * A[i];
                i--;
            } else {
                result[k++] = A[j] * A[j];
                j++;
            }
        }

        while (i >= 0) {
            result[k++] = A[i] * A[i];
            i--;
        }
        while (j < N) {
            result[k++] = A[j] * A[j];
            j++;
        }

        return result;
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     * <p>
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int length = nums.length;
        if (length < 3) {
            return nums[0];
        }
        for (int num : nums) {
            if (!countMap.containsKey(num)) {
                countMap.put(num, 1);
            } else {
                int count = countMap.get(num) + 1;
                if (count > length / 2) {
                    return num;
                }
                countMap.put(num, count);
            }
        }
        return 0;
    }

    /**
     * 编写一个程序，找到两个单链表相交的起始节点。
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode currNodeA = headA;
        ListNode currNodeB = headB;
        while (currNodeA != currNodeB) {
            currNodeA = currNodeA == null ? headB : currNodeA.next;
            currNodeB = currNodeB == null ? headA : currNodeB.next;
        }
        return currNodeA;

    }

    /**
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
     * <p>
     * 示例：
     * <p>
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return prehead.next;
    }

    public static ListNode recursionMergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = recursionMergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = recursionMergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    /**
     * 给你一个整数数组 arr，请你检查是否存在两个整数 N 和 M，满足 N 是 M 的两倍（即，N = 2 * M）。
     * <p>
     * 更正式地，检查是否存在两个下标 i 和 j 满足：
     * <p>
     * i != j
     * 0 <= i, j < arr.length
     * arr[i] == 2 * arr[j]
     *
     * @param arr
     * @return
     */
    public boolean checkIfExist(int[] arr) {
        if (arr.length == 0) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i == j) {
                    continue;
                }
                if (arr[i] == arr[j] * 2 || arr[j] == arr[i] * 2) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 给你两个长度相等的字符串 s 和 t。每一个步骤中，你可以选择将 t 中的 任一字符 替换为 另一个字符。
     * <p>
     * 返回使 t 成为 s 的字母异位词的最小步骤数。
     * <p>
     * 字母异位词 指字母相同，但排列不同的字符串。
     *
     * @param s
     * @param t
     * @return
     */
    public static int minSteps(String s, String t) {
        int result = 0;
        int[] letter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            letter[s.charAt(i) - 'a']++;
            letter[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (letter[i] > 0) {
                result += letter[i];
            }
        }
        return result;
    }

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * <p>
     * 你可以假设数组中无重复元素。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }


    /**
     * 算法图解练习4.1
     * <p>
     * 数组求和的递归形式
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

    /**
     * 算法图解
     * <p>
     * 快速排序实现
     *
     * @param array
     * @return
     */
    public static List<Integer> quickSort(List<Integer> array) {
        List<Integer> sortedArray = new ArrayList<>();
        if (array == null || array.size() < 2) {
            return array;
        } else {
            List<Integer> less = new ArrayList<>();
            List<Integer> greater = new ArrayList<>();
            int baseValue = array.get(0);
            for (int i = 1; i < array.size(); i++) {
                if (array.get(i) <= baseValue) {
                    less.add(array.get(i));
                } else {
                    greater.add(array.get(i));
                }
            }
            sortedArray.addAll(quickSort(less));
            sortedArray.add(baseValue);
            sortedArray.addAll(quickSort(greater));
            return sortedArray;
        }
    }

    /**
     * 算法图解
     * <p>
     * 迪克斯特拉算法实现
     *
     * @param array
     * @return
     */

    //初始化所有的节点信息
    private static final String[] nodes = {"start", "A", "B", "C", "E", "F", "end"};
    //已探测的节点
    private static Set<String> visitedNodes = new HashSet<String>();
    //设置没有路径的标志
    private static long NOWAY_SIGN = Long.MAX_VALUE;
    private static Map<String, String> parents = new HashMap<>();
    //地图
    private static Map<String, Map<String, Long>> graphs = new HashMap<>();
    //cost表
    private static Map<String, Long> costs = new HashMap<>();

    public static String findLowestCost(Map<String, Long> costs) {
        long lowestCost = NOWAY_SIGN;
        String lowestCostNode = null;
        for (Map.Entry<String, Long> entry : costs.entrySet()) {
            long cost = entry.getValue();
            if (cost < lowestCost && !visitedNodes.contains(entry.getKey())) {
                lowestCost = cost;
                lowestCostNode = entry.getKey();
            }
        }
        return lowestCostNode;
    }


    public static void dijkstra() {
        String nodeName = findLowestCost(costs);
        while (nodeName != null) {
            long cost = costs.get(nodeName);
            Map<String, Long> neighbors = graphs.get(nodeName);
            for (String neighbor : neighbors.keySet()) {
                long newCost = cost + neighbors.get(neighbor);
                if (costs.get(neighbor) > newCost) {
                    costs.put(neighbor, newCost);
                    parents.put(neighbor, nodeName);
                }
            }
            visitedNodes.add(nodeName);
            nodeName = findLowestCost(costs);
        }

    }


}
