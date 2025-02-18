/**
 * This class contains class (static) methods that will help you test the
 * Picture class methods. Uncomment the methods and the code in the main to
 * test.
 *
 * @author Barbara Ericson
 */
public class PictureTester {

    /**
     * Method to test the PictureExplorer
     */
    public static void testExplorer() {
        //Picture beach = new Picture("beach.jpg");
        //beach.explore();
        Picture street = new Picture("streets.jpg");
        Picture smallStreet = street.scale(0.50,0.50);
        smallStreet.write("streets.jpg");
        street.explore();   
    }

    /**
     * Method to test zeroBlue
     */
    public static void testZeroBlue() {
        Picture beach = new Picture("beach.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

    /**
     * Method to test mirrorVertical
     */
    public static void testMirrorVertical() {
        Picture caterpillar = new Picture("caterpillar.jpg");
        caterpillar.explore();
        caterpillar.mirrorVertical();
        caterpillar.explore();
    }

    /**
     * Method to test mirrorTemple
     */
    public static void testMirrorTemple() {
        Picture temple = new Picture("temple.jpg");
        temple.explore();
        temple.mirrorTemple();
        temple.explore();
    }

    /**
     * Method to test edgeDetection
     */
    public static void testEdgeDetection() {
        Picture swan = new Picture("swan.jpg");
        swan.edgeDetection(10);
        swan.explore();
    }

    /**
     * Method to test scaling a picture
     */
    public static void testScale() {
        Picture temple = new Picture("temple.jpg");
        temple.explore();
        Picture temple2 = temple.scale(0.5, 0.5);
        temple2.explore();
    }

    /**
     * Method to test keepOnlyBlue
     */
    public static void testKeepOnlyBlue() {
        Picture cC = new Picture("cyberchina.jpg");
        cC.explore();
        cC.keepOnlyBlue();
        cC.explore();
    }

    /**
     * Method to test keepOnlyRed
     */
    public static void testKeepOnlyRed() {
        Picture mccp = new Picture("McCp.jpg");
        mccp.explore();
        mccp.keepOnlyRed();
        mccp.explore();
    }

    /**
     * Method to test keepOnlyGreen
     */
    public static void testKeepOnlyGreen() {
        Picture sP = new Picture("solarpunk.jpg");
        sP.explore();
        sP.keepOnlyGreen();
        sP.explore();
    }

    /**
     * Method to test FixUnderwater
     */
    public static void testFixUnderwater() {
        Picture water = new Picture("water.jpg");
        water.explore();
        water.fixUnderwater();
        water.explore();
    }

    /**
     * Method to test negate
     */
    public static void testNegate() {
        Picture g = new Picture("gorge.jpg");
        g.explore();
        g.negate();
        g.explore();
    }
    
    /**
     * Method to test grayscale
     */
    public static void testGrayscale() {
        Picture sg = new Picture("seagull.jpg");
        sg.explore();
        sg.grayscale();
        sg.explore();
    }

    /**
     * Main method for testing. Every class can have a main method in Java
     */
    public static void main(String[] args) {
        // uncomment a call here to run a test
        // and comment out the ones you don't want
        // to run

        //testExplorer();
        //testScale();
        //testZeroBlue();
        //testKeepOnlyBlue();
        //testKeepOnlyRed();
        //testKeepOnlyGreen();
        //testNegate();
        //testGrayscale();
        testFixUnderwater();
        //testMirrorVertical();
        //testMirrorTemple();
        //testMirrorArms();
        //testMirrorGull();
        //testMirrorDiagonal();
        //testCollage();
        //testCopy();
        //testEdgeDetection();
        //testEdgeDetection2();
        //testChromakey();
        //testEncodeAndDecode();
        //testGetCountRedOverValue(250);
        //testSetRedToHalfValueInTopHalf();
        //testClearBlueOverValue(200);
        //testGetAverageForColumn(0);
    }
}
