import java.util.Deque;
import java.util.ArrayDeque;

/* -----------------------------------------
   Note: The ArrayDeque is an implementation 
         of the Deque ADT. That is, it is a 
		   double-ended queue. 

		   You can simulate both a Stack and 
		   a regular Queue with this data structure
		   in the following way:

		 Stack: push  ~ addFirst
		        pop   ~ removeFirst
		
		 Queue: enqueue ~ addLast
		        dequeue ~ removeFirst
  ------------------------------------------ */

public class MyBlobs extends Blobs{


	///////////////////////////////////////////////////////////////////////	
	///////////////////////////////////////////////////////////////////////	
	// do NOT change or remove this constructor. We will use it to create 
	// objects when testing your code. If it is removed, we cannot test 
	// your code!
	///////////////////////////////////////////////////////////////////////	
	public MyBlobs(){}
	///////////////////////////////////////////////////////////////////////	
	///////////////////////////////////////////////////////////////////////	
	@Override
	public void blobRecursiveHelper(int row, int col, Deque<Pixel> blobSoFar) {
	if (shouldVisit(row, col)) {
	// if inside this loop, means it has ink but not visited yet
	Pixel currPixel = image.getPixel(row, col);
	// so mark it as visited
	currPixel.setVisited(true);
	// add it to the blob
	blobSoFar.addLast(currPixel);
	int currRow = currPixel.getRow();
	int currCol = currPixel.getCol();
	// and then visited all neighbours of current pixel
	// who satisfy shouldVisit(neighbour coordinates)
	
	//up
	if (shouldVisit(currRow - 1, currCol))
	blobRecursiveHelper(currRow - 1, currCol, blobSoFar);
	//right
	if (shouldVisit(currRow, currCol + 1))
	blobRecursiveHelper(currRow, currCol + 1, blobSoFar);
	//down
	if (shouldVisit(currRow + 1, currCol))
	blobRecursiveHelper(currRow + 1, currCol, blobSoFar);
	//left
	if (shouldVisit(currRow, currCol - 1))
	blobRecursiveHelper(currRow, currCol - 1, blobSoFar);
	}
	}
	/**
* I'VE MODIFIED THIS FUNCTION
*
* For a given (row,col), this function check if this position is
* a valid cell/pixel in the image and if it's not, return false.
* If valid, should visit only if pixel at row,col hasInk and is
* NOT visited yet;
*
* @param row
* @param col
* @return true if this pixel at row,col should be visited else false
*/
private boolean shouldVisit(int row, int col) {
	if (0 <= row && row < image.rows && 0 <= col && col < image.cols) {
	Pixel pixel = image.getPixel(row, col);
	return pixel.hasInk() && !pixel.visited();
	}
	return false;
	}
	
	@Override
    public Deque<Pixel> blobIterative(int row, int col) {
        // empty lists
        Deque<Pixel> blobList = new ArrayDeque<>();
        Deque<Pixel> workingList = new ArrayDeque<>();
        // add pixel to workingList
        workingList.addLast(new Pixel(row, col));

        // order or items to visit
        int[][] directions = new int[][] {
            {0, -1}, // left means decreasing the column value
            {+1, 0}, // down means increasing the row value
            {0, +1}, // right means increasing the column value
            {-1, 0} // up means decreasing the row value
        };

        // while workingList is not empty
        while (!workingList.isEmpty()) {
            // remove the pixel from the working list
            Pixel p = workingList.removeFirst();
            // if p has ink and has not been visited
            if (p.hasInk() && !p.visited()) {
                // add p to back of blob lists
                blobList.addLast(p);
                // for each immediate neighbor q of p
                for (int[] dir : directions) {
                    int qRow = row + dir[0];
                    int qCol = col + dir[1];
                    // add q to workingList
                    workingList.addLast(new Pixel(qRow, qCol));
                }
            }
        }

        // output the blobList
        return blobList;
    }
	}

	
	