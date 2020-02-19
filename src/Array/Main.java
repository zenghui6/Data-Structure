package Array;

public class Main {

    public static void main(String[] args) {
	    Array<Integer> arr = new Array<Integer>();
	    for (int i = 0 ; i<10 ; i++){
	        arr.addLast(i);
        }
        System.out.println(arr);

	    arr.add(1,100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.addFirst(3);
        System.out.println(arr);
        System.out.println(arr.findAll(3));

    }
}
