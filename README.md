# DynamicProxy
Implemented Dynamic proxy using Java Reflection

The aim of this project is to create a generic library to serialize and deserialize objects. 
The code allows the conversion of objects into a wire format. The code is designed using dynamic 
proxies and reflection so that addition of new objects or serialization formats causes minimal 
changes (reduces the ripple effect).

The driver code invokes methods on the dynamic proxy, as if it is invoking methods on an 
object that implements the 2 interfaces (StoreI and RestoreI). 
Each invocation will transfer control to the invoke method of the invocation handler.

-----------------------------------------------------------------------

Assuming you are in the directory containing this README:

## To clean:
ant -buildfile genericCheckpointing/src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile genericCheckpointing/src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
ant -buildfile genericCheckpointing/src/build.xml run -Darg0=firstarg -Darg1=SECOND -Darg2=THIRD

