package com.springboot.test.interviewQuestion;

/**
 * NodeAddTest
 *
 * @author txw
 * @date 2021/7/1 18:26
 */
public class NodeAddTest {

	public static void main(String[] args) {

	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode result = null;
		ListNode listNode = null;
		// 进一位
		int flag = 0;
		int count = 0;
		while (l1 != null || l2 != null) {
			if (l1 == null) {
				l1 = new NodeAddTest.ListNode(0);
			}
			if (l2 == null) {
				l2 = new NodeAddTest.ListNode(0);
			}
			count = l1.val + l2.val + flag;
			flag = 0;
			if (count >= 10) {
				count = count - 10;
				flag = 1;
			}
			l1 = l1.next;
			l2 = l2.next;
			if (result == null) {
				result = new ListNode(count);
				listNode = result;
			} else {
				listNode.next = new ListNode(count);
				listNode = listNode.next;
			}
		}
		if (flag == 1) {
			listNode.next = new ListNode(flag);
		}
		return result;
	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}

}


