package project6;

import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedTriangleArray;
import javax.vecmath.Point3f;

public class Exam extends IndexedTriangleArray {

    public Exam() {
        super(5,GeometryArray.COORDINATES, 18); // 6 vertices, 3*8 indices
        // X //
        setCoordinate(0, new Point3f(0,1,0));

        setCoordinate(1, new Point3f(1,0,1));

        setCoordinate(2, new Point3f(-1,0,1));

        setCoordinate(3, new Point3f(-1,0,-1));

        setCoordinate(4, new Point3f(1,0,-1));
        
        int[] coord = {0,1,0,2,0,3,0,4,1,2,1,3,1,4,2,3,2,4};
        	
        setCoordinateIndices(0, coord);

    }
}