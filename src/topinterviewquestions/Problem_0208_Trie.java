package topinterviewquestions;

/**
 * [208. 实现 Trie (前缀树)](https://leetcode.cn/problems/implement-trie-prefix-tree/)

**[Trie](https://baike.baidu.com/item/字典树/9825209?fr=aladdin)**（发音类似 "try"）或者说 **前缀树** 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补全和拼写检查。

请你实现 Trie 类：

- `Trie()` 初始化前缀树对象。
- `void insert(String word)` 向前缀树中插入字符串 `word` 。
- `boolean search(String word)` 如果字符串 `word` 在前缀树中，返回 `true`（即，在检索之前已经插入）；否则，返回 `false` 。
- `boolean startsWith(String prefix)` 如果之前已经插入的字符串 `word` 的前缀之一为 `prefix` ，返回 `true` ；否则，返回 `false` 。
 */
/*
 * 提交时把类名、构造函数名从Problem_0208_Trie改为Trie
 * 
 * */
public class Problem_0208_Trie {

	public static class Node {
		public boolean end;
		public Node[] nexts;

		public Node() {
			end = false;
			nexts = new Node[26];
		}
	}

	private Node root;

	public Problem_0208_Trie() {
		root = new Node();
	}

	public void insert(String word) {
		if (word == null) {
			return;
		}
		char[] str = word.toCharArray();
		Node node = root;
		int path = 0;
		for (int i = 0; i < str.length; i++) {
			path = str[i] - 'a';
			if (node.nexts[path] == null) {
				node.nexts[path] = new Node();
			}
			node = node.nexts[path];
		}
		node.end = true;
	}

	public boolean search(String word) {
		if (word == null) {
			return false;
		}
		char[] chs = word.toCharArray();
		Node node = root;
		int index = 0;
		for (int i = 0; i < chs.length; i++) {
			index = chs[i] - 'a';
			if (node.nexts[index] == null) {
				return false;
			}
			node = node.nexts[index];
		}
		return node.end;
	}

	public boolean startsWith(String pre) {
		if (pre == null) {
			return false;
		}
		char[] chs = pre.toCharArray();
		Node node = root;
		int index = 0;
		for (int i = 0; i < chs.length; i++) {
			index = chs[i] - 'a';
			if (node.nexts[index] == null) {
				return false;
			}
			node = node.nexts[index];
		}
		return true;
	}

}
