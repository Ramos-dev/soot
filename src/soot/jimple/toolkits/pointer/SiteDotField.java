package soot.jimple.toolkits.pointer;
import soot.toolkits.graph.*;
import soot.*;
import soot.jimple.*;
import java.util.*;

public class SiteDotField
{
    protected Type type;
    public AllocNode o1;
    public Object o2;
    public SiteDotField( AllocNode o1, Object o2 ) { 
	if( o1 == null ) throw new RuntimeException( "No, you can't make it null" );
	if( o2 == null ) throw new RuntimeException( "No, you can't make it null" );
	this.o1 = o1; 
	this.o2 = o2;
	if( o2 instanceof SootField ) {
	    SootField f = (SootField) o2;
	    type = f.getType();
	} else {
	    Type t = o1.getType();
	    if( !(t instanceof ArrayType) ) {
		// OK, here we're creating a siteDotField a.[], where
		// a is not of array type. If we're not actually doing
		// type-filtering, this can actually happen. So, what type
		// does a.[] have? Well, since we're not doing type filtering,
		// it doesn't really make any difference, so we may as well
		// give it type java.lang.Object.
		type = Scene.v().getSootClass( "java.lang.Object" ).getType();
	    } else {
		ArrayType arrayType = (ArrayType) o1.getType();
		type = arrayType.baseType;
	    }
	}
    }
    public int hashCode() {
	return o1.hashCode() + o2.hashCode();
    }
    public boolean equals( Object other ) {
	if( other instanceof SiteDotField ) {
	    SiteDotField p = (SiteDotField) other;
	    return o1.equals( p.o1 ) && o2.equals( p.o2 );
	} else return false;
    }
    public Type getType() {
	return type;
    }
    public String toString() {
	return "Site dot field "+o1+" field "+o2;
    }
}
