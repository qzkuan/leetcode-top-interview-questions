package topinterviewquestions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * [47. 全排列 II](https://leetcode.cn/problems/permutations-ii/)

给定一个可包含重复数字的序列 `nums` ，***按任意顺序*** 返回所有不重复的全排列。
 */
public class Problem_0047_Permutations_unique {
    List<Integer> nums;
    List<List<Integer>> res;

    void swap(int a, int b) {
        int tmp = nums.get(a);
        nums.set(a, nums.get(b));
        nums.set(b, tmp);
    }
    /**
     * 深度优先遍历
     * @param x 当前固定位
     */
    void dfs(int x) {
        if (x == nums.size() - 1) {
            res.add(new ArrayList<>(nums));  // 所有位已固定，添加排列方案
            return;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = x; i < nums.size(); i++) {
            if (set.contains(nums.get(i)))
                continue;            // 重复，因此剪枝
            set.add(nums.get(i));
            swap(i, x);              // 交换，将 nums[i] 固定在第 x 位
            dfs(x + 1);              // 开启固定第 x + 1 位元素
            swap(i, x);              // 恢复交换
        }
    }

    /**
     * 无重复全排列
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        this.res = new ArrayList<>();
        this.nums = new ArrayList<>();
        for (int num : nums) {
            this.nums.add(num);
        }
        dfs(0);
        return res;
    }
}