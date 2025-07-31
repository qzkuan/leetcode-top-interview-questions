package test;

import java.util.HashMap;

public class LRUCache {
    private HashMap<Integer, Node<Integer, Integer>> keyNodeMap;
    private NodeDoubleLinkedList<Integer, Integer> nodeList;
    private final int capacity;

    public LRUCache(int capacity) {
        if (capacity < 1) {
            throw new RuntimeException("should be more than 0.");
        }
        keyNodeMap = new HashMap<Integer, Node<Integer, Integer>>();
        nodeList = new NodeDoubleLinkedList<Integer, Integer>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (keyNodeMap.containsKey(key)) {
            // 缓存里有该node，最近被使用，把该node移动到链表尾部
            Node<Integer, Integer> res = keyNodeMap.get(key);
            nodeList.moveNodeToTail(res);
            return res.value;
        }
        return 0;
    }

    public void put(int key, int value) {
        if (keyNodeMap.containsKey(key)) {
            // 缓存里已有node，更新node值，并移动到链表尾部
            Node<Integer, Integer> node = keyNodeMap.get(key);
            node.value = value;
            nodeList.moveNodeToTail(node);
        } else { // 缓存里没有node
            if (keyNodeMap.size() == capacity) {
                removeMostUnusedCache(); // 先检查容量，不够则删除最久未被使用结点
            }
            // 构建结点，插入到双向链表尾部
            Node<Integer, Integer> newNode = new Node<Integer, Integer>(key, value);
            keyNodeMap.put(key, newNode);
            nodeList.addNode(newNode);
        }
    }

    /**
     * 删除最近最久未被使用的结点（即链表头结点）
     */
    private void removeMostUnusedCache() {
        Node<Integer, Integer> removeNode = nodeList.removeHead();
        keyNodeMap.remove(removeNode.key);
    }

    public class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> last;
        public Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    class NodeDoubleLinkedList<K, V> {
        private Node<K,V> head;
        private Node<K,V> tail;
        public NodeDoubleLinkedList(){
            head=null;
            tail=null;
        }
        public void addNode(Node<K,V> newNode){
            if(newNode==null){
                return;
            }
            if(head==null){
                head=newNode;
                tail=newNode;
            }else{
                tail.next=newNode;
                newNode.last=tail;
                tail=newNode;
            }
        }
        public void moveNodeToTail(Node<K,V> node){
            if(node == tail){
                return;
            }
            if(head==node){
                head=node.next;
                head.last=null;
            }else{
                node.next.last=node.last;
                node.last.next=node.next;
            }
            node.last=tail;
            node.next=null;
            tail.next=node;
            tail=node;
        }
        	/**
		 * 移除头结点（用于表满的时候删除最近最久未被使用的节点）
		 * @return
		 */
		public Node<K, V> removeHead() {
			if (head == null) {
				return null;
			}
			Node<K, V> res = head; // res指向要移除的结点
			if (head == tail) { // 链表中只有一个节点的时候
				head = null;
				tail = null;
			} else { // 链表不止一个结点，头指针指向下一个结点，移除该结点
				head = res.next;
				res.next = null;
				head.last = null;
			}
			return res;
		}
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
