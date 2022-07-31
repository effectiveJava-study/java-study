package com.example;

import javax.sound.sampled.Line;

public class LinkedList {
    private int elements;

    private LinkedList next = null;

    public LinkedList(int data){
        elements = data;
    }

    public LinkedList add(LinkedList newElement){
        if(this.next == null){
            this.next = newElement;
            return this;
        }

        LinkedList nextNode = this.next;
        while(nextNode.next != null){
            nextNode = nextNode.next;
        }

        nextNode.next = newElement;

        return this;
    }

    public LinkedList add(LinkedList head, LinkedList nodeToAdd, int position){
        LinkedList nextNode = head;

        for(int loop = 0; loop < position - 1; loop++){
            if(nextNode.next == null){
                break;
            }
            nextNode = nextNode.next;
        }

        LinkedList tmp = nextNode.next;
        nextNode.next = nodeToAdd;
        nodeToAdd.next = tmp;

        return this;
    }

    public LinkedList remove(LinkedList head, int positionToRemove){
        LinkedList nextNode = head;

        for(int loop = 0; loop < positionToRemove - 1; loop++){
            nextNode = nextNode.next;
        }
        //현재 시점의 nextNode 에서 next 가 지워져야할 node
        LinkedList tmp = nextNode.next;
        nextNode.next = tmp.next;
        tmp = null;

        return this;
    }

    public boolean contains(LinkedList head, LinkedList nodeToCheck){
        LinkedList nextNode = head;

        while(nextNode.next != null){
            if (nextNode.elements == nodeToCheck.elements){
                return true;
            }
            nextNode = nextNode.next;
        }

        return false;
    }

    public void printForEach(){
        LinkedList nextNode = this;

        while(nextNode != null){
            System.out.println(nextNode.elements);
            nextNode = nextNode.next;
        }
    }

    public int size(){
        LinkedList nextNode = this;

        int size = 0;
        while(nextNode != null){
            ++size;
            nextNode = nextNode.next;
        }

        return size;
    }
}
