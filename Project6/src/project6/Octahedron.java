package project6;

import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedTriangleArray;
import javax.vecmath.Point3f;

/**
 *
 * @author Elvis Mack
 */
public class Octahedron extends IndexedTriangleArray {

    public Octahedron() {
        super(6, GeometryArray.COORDINATES, 24); // 6 vertices, 3*8 indices
        // X //
        setCoordinate(0, new Point3f(-1f, 0f, 0f));
        setCoordinate(1, new Point3f(1f, 0f, 0f));
        // Y //
        setCoordinate(2, new Point3f(0f, -1f, 0f));
        setCoordinate(3, new Point3f(0f, 1f, 0f));
        // Z //
        setCoordinate(4, new Point3f(0f, 0f, -1f));
        setCoordinate(5, new Point3f(0f, 0f, 1f));
        
        int[] coord = {0,2,4,0,2,5,0,3,4,0,3,5,1,2,4,1,2,5,1,3,4,1,3,5};
        	
        setCoordinateIndices(0, coord);

    }
}
