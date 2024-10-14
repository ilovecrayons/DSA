public class RawNode {
    String rawContent;
    RawNode prev = null;
    RawNode next = null;

    RawNode(String rawContent) {
        this.rawContent = rawContent;
    }
    
    String getRawContent(){
        return rawContent;
    }

    RawNode getNext(){
        return next;
    }
    RawNode getPrev(){
        return prev;
    }
    RawNode addNext(RawNode other){
        next = other;
        other.prev = this;
        return other;
    }
    RawNode addPrev(RawNode other){
        prev = other;
        other.next = this;
        return other;
    }

    static RawNode createNode(String rawContent){
        return new RawNode(rawContent);
    }

    
}
