import java.util.Deque;
public class Sample{

	public static Blobs newBlob(){
		return new MyBlobsSol();
		//return new MyBlobs();
	} 

	public static void main(String[] args){
      
		Image img = Image.makeTestImage();

		// some staring points
		int[][] start = {{1,2}, {7,5}, {4,13}, {5,18}, {7,20}, {11,15}, {11,2}};

		System.out.println(img);
		Deque<Pixel> blob = null;
		for(int[] rc : start){
			img = Image.makeTestImage();
			Blobs b = newBlob();
			b.setImage(img);
         
			blob = b.blobRecursive(rc[0], rc[1]);
            //blob = b.blobIterative(rc[0], rc[1]);
         	System.out.println("start at : " + rc[0] + "," + rc[1]);
			System.out.println(blob);
         	System.out.println(img.show_walk(blob));
			
			img.clearImage();
			img.clearExtra();
		}
      //System.out.println(img.show_walk(blob));
	}


}
