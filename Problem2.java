//Time Complexity: O(n)
//Space Complexity:O(k)

class SkipIterator implements Iterator<Integer> {
    HashMap<Integer, Integer> skipMap;
    Integer nextEl;
    Iterator<Integer> nit;
	public SkipIterator(Iterator<Integer> it) {
	    this.skipMap = new HashMap<>();
        this.nit = it;
        advance();
    
    }
    private void advance(){
        nextEl = null;
        while(nextEl == null && nit.hasNext()){
            Integer el = nit.next();
            if(skipMap.containsKey(el)){
                skipMap.put(el, skipMap.get(el) - 1);
                skipMap.remove(el, 0);
            }
            else{
                nextEl = el;
            }
        }
    }

	public boolean hasNext() {
        return nextEl != null;
	}

	public Integer next() {
        Integer result = nextEl;
        advance();
        return result;
	}
