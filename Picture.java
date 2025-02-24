import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 *
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments
     */
    public Picture() {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();
    }

    /**
     * Constructor that takes a file name and creates the picture
     *
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName) {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes a file name and creates the picture
     *
     * @param file the name of the file to create the picture from
     */
    public Picture(java.io.File file) {
        // let the parent class handle this fileName
        super(file);
    }

    /**
     * Constructor that takes the width and height
     * 
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int width, int height) {
        // let the parent class handle this width and height
        super(width, height);
    }

    /**
     * Constructor that takes a picture and creates a copy of that picture
     *
     * @param copyPicture the picture to copy
     */
    public Picture(SimplePicture copyPicture) {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     *
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image) {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////
    /**
     * Method to return a string with information about this picture.
     *
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString() {
        String output = "Picture, filename " + getFileName()
            + " height " + getHeight()
            + " width " + getWidth();
        return output;

    }

    /**
     * Method to create a new picture by scaling the current picture by the given
     * x and y factors
     *
     * @param xFactor the amount to scale in x
     * @param yFactor the amount to scale in y
     * @return the resulting picture
     */
    public Picture scale(double xFactor, double yFactor) {
        // set up the scale transform
        AffineTransform scaleTransform = new AffineTransform();
        scaleTransform.scale(xFactor, yFactor);

        // create a new picture object that is the right size
        Picture result = new Picture((int) (getWidth() * xFactor),
                (int) (getHeight() * yFactor));

        // get the graphics 2d object to draw on the result
        Graphics graphics = result.getGraphics();
        Graphics2D g2 = (Graphics2D) graphics;

        // draw the current image onto the result image scaled
        g2.drawImage(this.getImage(), scaleTransform, null);

        result.setTitle(getTitle());
        return result;
    }

    /**
     * Method to set the blue to 0
     */
    public void zeroBlue() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setBlue(0);
            }
        }
    }

    /**
     * Removes all the red from this image.
     */
    public void zeroRed() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setRed(0);
            }
        }
    }

    /**
     * Removes all the green from this image.
     */
    public void zeroGreen() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setGreen(0);
            }
        }   
    }

    /**
     * keeps all the blue from this image.
     */
    public void keepOnlyBlue() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setGreen(0);
                pixelObj.setRed(0);
            }
        }   
    }

    /**
     * keeps all the red from this image.
     */
    public void keepOnlyRed() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setBlue(0);
                pixelObj.setGreen(0);
            }
        }   
    }

    /**
     * keeps all the green from this image.
     */
    public void keepOnlyGreen() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setBlue(0);
                pixelObj.setRed(0);
            }
        }   
    }

    /**
     * negates every pixel in this image.
     */
    public void negate() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setBlue(255-pixelObj.getBlue());
                pixelObj.setRed(255-pixelObj.getRed());
                pixelObj.setGreen(255-pixelObj.getGreen());
            }
        }   
    }
    
    /**
     * makes the image more "natural"
     */
    public void fixUnderwater() {
        Pixel[][] pixels = this.getPixels2D();
        
        // Initialize min and max values for each color channel
        int minRed = 255, maxRed = 0;
        int minGreen = 255, maxGreen = 0;
        int minBlue = 255, maxBlue = 0;
    
        // First pass: Find the min and max values for each color channel
        for (Pixel[] row : pixels) {
            for (Pixel pixel : row) {
                int red = pixel.getRed();
                int green = pixel.getGreen();
                int blue = pixel.getBlue();
    
                // Update min and max for red
                if (red < minRed) minRed = red;
                if (red > maxRed) maxRed = red;
    
                // Update min and max for green
                if (green < minGreen) minGreen = green;
                if (green > maxGreen) maxGreen = green;
    
                // Update min and max for blue
                if (blue < minBlue) minBlue = blue;
                if (blue > maxBlue) maxBlue = blue;
            }
        }
    
        // Second pass: Adjust each pixel's color based on the min and max values
        for (Pixel[] row : pixels) {
            for (Pixel pixel : row) {
                int red = pixel.getRed();
                int green = pixel.getGreen();
                int blue = pixel.getBlue();
    
                // Normalize the color values to the range [0, 255]
                red = (int) ((red - minRed) * 255.0 / (maxRed - minRed));
                green = (int) ((green - minGreen) * 255.0 / (maxGreen - minGreen));
                blue = (int) ((blue - minBlue) * 255.0 / (maxBlue - minBlue));
    
                // Ensure the values are within the valid range [0, 255]
                red = Math.min(Math.max(red, 0), 255);
                green = Math.min(Math.max(green, 0), 255);
                blue = Math.min(Math.max(blue, 0), 255);
    
                // Set the new color values
                pixel.setRed(red);
                pixel.setGreen(green);
                pixel.setBlue(blue);
            }
        }
    }

    /**
     * makes the image grayscale / "vintage" ig
     */
    public void grayscale() {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setRed((int) pixelObj.getAverage());
                pixelObj.setGreen((int) pixelObj.getAverage());
                pixelObj.setBlue((int) pixelObj.getAverage());
            }
        }   
    }
    
        /**
         * makes the image monochromatic
         */
        public void baW() {
            Pixel[][] pixels = this.getPixels2D();
            for (Pixel[] rowArray : pixels) {
                for (Pixel pixelObj : rowArray) {
                    if(pixelObj.getAverage()>127) {
                        pixelObj.setColor(Color.white);
                    }
                    if(pixelObj.getAverage()<=127) {
                        pixelObj.setColor(Color.black);
                    }
                }
            }   
        }

    /**
     * Method that mirrors the picture around a vertical mirror in the center of
     * the picture from left to right
     */
    public void mirrorVertical() {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < width / 2; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    /**
     *  Creates a vertical mirror image of the this picture.
     */
    public void verticalReflection() {
        Pixel[][] pixels = this.getPixels2D();
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < width / 2; col++) {
                // Copy pixel from the right to the left
                Pixel leftPixel = pixels[row][col];
                Pixel rightPixel = pixels[row][width - 1 - col];
                leftPixel.setColor(rightPixel.getColor());
            }
        }
    }

    /**
     * Converts this image into a horizontal mirror image of itself.
     */
    public void mirrorHorizontal() {
        Pixel[][] pixels = this.getPixels2D();
        int height = pixels.length;
        for (int row = 0; row < height / 2; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                // Copy pixel from the top to the bottom
                Pixel topPixel = pixels[row][col];
                Pixel bottomPixel = pixels[height - 1 - row][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
    }

    public void mirrorHorizontalb2t() {
        Pixel[][] pixels = this.getPixels2D();
        int height = pixels.length;
        for (int row = 0; row < height / 2; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                // Copy pixel from the top to the bottom
                Pixel topPixel = pixels[row][col];
                Pixel bottomPixel = pixels[height - 1 - row][col];
                topPixel.setColor(bottomPixel.getColor());
            }
        }
    }

    /**
     * Mirror just part of a picture of a temple
     */
    public void mirrorTemple() {
        Pixel[][] pixels = this.getPixels2D();
        int count = 0; // Counter for loop executions
        int mirrorPoint = 276; // Column to mirror around
        for (int row = 27; row < 97; row++) {
            for (int col = 13; col < mirrorPoint; col++) {
                count++; // Increment counter
                Pixel leftPixel = pixels[row][col];
                Pixel rightPixel = pixels[row][mirrorPoint + (mirrorPoint - col)];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
        System.out.println("Number of loop executions: " + count); // Print the count
    }

    /**
     * Mirror just part of a picture of a snowman
     */
    public void mirrorArms() {
        Pixel[][] pixels = this.getPixels2D();
        int mirrorPoint = 193; // Row to mirror around
    
        // Mirror left arm to right arm
        for (int row = 158; row < mirrorPoint; row++) {
            for (int col = 103; col < 170; col++) {
                Pixel topPixel = pixels[row][col];
                Pixel bottomPixel = pixels[mirrorPoint + (mirrorPoint - row)][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        }
    }


    /**
     * Mirror just part of a picture of a seagull
     */
    public void mirrorGull() {
        Pixel[][] pixels = this.getPixels2D();
        int mirrorPoint = 350; // Column to mirror around
    
        // Mirror left side of the seagull to the right side
        for (int row = 230; row < 330; row++) {
            for (int col = 230; col < mirrorPoint; col++) {
                Pixel leftPixel = pixels[row][col];
                Pixel rightPixel = pixels[row][mirrorPoint + (mirrorPoint - col)];
                rightPixel.setColor(leftPixel.getColor());
            }   
        }
    }
    /**
     * copy from the passed fromPic to the specified startRow and startCol in the
     * current picture
     *
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic, int startRow, int startCol) {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow;
        fromRow < fromPixels.length
        && toRow < toPixels.length;
        fromRow++, toRow++) {
            for (int fromCol = 0, toCol = startCol;
            fromCol < fromPixels[0].length
            && toCol < toPixels[0].length;
            fromCol++, toCol++) {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }
    }

    /**
     * 
     * @param fromPic The source image we are copying from
     * @param destRow the start row to copy to
     * @param destCol the start col to copy to
     * @param fromRow The start row of fromPic
     * @param fromCol The start col of fromPic
     * @param w Width of the area we wish to copy.
     * @param h Height of the area we wish to copy.
     */
    public void copy(Picture fromPic,int destRow, int destCol, int fromRow, int fromCol, int w, int h) {
        //TODO: Write and test this method
    }

    /**
     * Method to show large changes in color
     *
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist) {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0;
            col < pixels[0].length - 1; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col + 1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor)
                > edgeDist)
                    leftPixel.setColor(Color.BLACK);
                else
                    leftPixel.setColor(Color.WHITE);
            }
        }
    }

    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

} // this } is the end of class Picture, put all new methods before this