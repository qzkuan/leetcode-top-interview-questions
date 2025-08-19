package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * [79. 单词搜索](https://leetcode.cn/problems/word-search/)

给定一个 `m x n` 二维字符网格 `board` 和一个字符串单词 `word` 。如果 `word` 存在于网格中，返回 `true` ；否则，返回 `false` 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 */
public class Temp {
    static List<String> resultStaticList=new ArrayList<>();
	public static void main(String[] args){
		// 在字符串常量池中创建字符串对象 ”ab“
		// 将字符串对象 ”ab“ 的引用赋值给给 aa
		String aa = "ab打击国际撒个；";
		// 直接返回字符串常量池中字符串对象 ”ab“，赋值给引用 bb
		String bb = "ab打击国际撒个；";
		String cc = bb.intern();
		System.out.println(aa == bb); // true
		System.out.println(aa == cc); // true
		char board[][]={{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words ={"oath","pea","eat","rain"};
        List<String> resuList = findWords(board, words);
		System.out.println(resuList);
	}

	static Trie trie=new Trie();

	public static List<String> findWords(char[][] board, String[] words) {
		List<String> result=new ArrayList<>();
		for(String word:words){
			trie.insert(word);
		}
        for (String word : words) {
		    if(exist(board, word)){
                result.add(word);
            }
        }
		return result;
        
    }

	public static boolean exist(char[][] board, String word) {
		char[] w = word.toCharArray();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (process(board, i, j, w, 0)) {
					return true;
				}
			}
		}
		return false;
	}

	// 目前到达了b[i][j]，word[k....]
	// 从b[i][j]出发，能不能搞定word[k....]  true  false
	public static boolean process(char[][] b, int i, int j, char[] w, int k) {
        if(k == w.length) {
			return true;
		}
        // k 有字符
		if (i < 0 || i == b.length || j < 0 || j == b[0].length) {
			return false;
		}
		if (b[i][j] != w[k]) {
			return false;
		}
        String prefix= getFirstElements(w,k);
        if(!trie.startsWith(prefix)){
            return false;
        }
		// 标记当前位置已走过
		char tmp = b[i][j];
		b[i][j] = 0;
		boolean ans =  process(b, i - 1, j, w, k + 1) 
				|| process(b, i + 1, j, w, k + 1) 
				|| process(b, i, j - 1, w, k + 1)
				|| process(b, i, j + 1, w, k + 1);
		// 取消标记当前位置，恢复现场
		b[i][j] = tmp;
		return ans;
	}
    public static String getFirstElements(char[] array,int length) {
        StringBuilder sb = new StringBuilder();
        length = Math.min(array.length, length); // 确保不会越界
        for (int i = 0; i < length; i++) {
            sb.append(array[i]);
        }
        return sb.toString();
    }


static class Trie{
	public static class Node {
		public boolean end;
		public Node[] nexts;

		public Node() {
			end = false;
			nexts = new Node[26];
		}
	}

	private Node root;

	public Trie() {
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
}
