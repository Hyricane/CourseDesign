package com.itheima.constant;
class Demo {
    class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
    private TreeNode lca = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        //借助findNode方法找，找到了就把结果放在lca中
        findNode(root, p, q);
        return lca;
    }
    //看从root出发能否找到P或q，只要找到一个就返回true，都找不到就返回false；
    private boolean findNode(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }

        int left = findNode(root.left, p, q) ? 1 : 0;     //递归按照后序遍历的方式进行
        int right = findNode(root.right, p, q) ? 1 : 0;
        int mid = (root == p || root == q) ? 1 : 0;
        if (left + right + mid == 2) {
            lca = root;
        }
        //相加为0；当前节点不是p q的祖先
        //相加为1；当前节点可能是p的祖先，也可能是q的祖先，也可能是p q的祖先，但肯定不是p q的公共祖先
        //相加为2；当前节点是p q的公共祖先
        return (left + right + mid) > 0;
        //如果三个位置之和为0；说明没找到，返回false
        //只要找到一个或者以上都返回true；
    }
}