# DynamicProxy
Implemented Dynamic proxy using Java Reflection

The aim of this project is to create a generic library to serialize and deserialize objects. 
The code is designed using dynamic proxies and reflection so that addition of new objects or serialization formats causes minimal changes (reduces the ripple effect) . Strategy pattern is used for Serialization and Deserialization.

The driver code invokes methods on the dynamic proxy, as if it is invoking methods on an 
object that implements the 2 interfaces (StoreI and RestoreI). 
Each invocation will transfer control to the invoke method of the invocation handler.

The code allows the conversion of objects into a wire format. 
Designed two Java classes MyAllTypesFirst and MyAllTypesSecond with data members that have names and types as shown in the serialized format below:
  <DPSerialization>
    <complexType xsi:type="genericCheckpointing.util.MyAllTypesFirst">
      <myInt xsi:type="xsd:int">314</myInt>
      <myLong xsi:type="xsd:long">314159</myLong>
      <myString xsi:type="xsd:string">Design Patterns</myString>
      <myBool xsi:type="xsd:boolean">false</myBool>
      <myOtherInt xsi:type="xsd:int">314</myOtherInt>
    </complexType>
  </DPSerialization>
  <DPSerialization>
    <complexType xsi:type="genericCheckpointing.util.MyAllTypesSecond">
      <myDoubleT xsi:type="xsd:double">3.1459</myDoubleT>
      <myFloatT xsi:type="xsd:float">3145.9</myFloatT>
      <myShortT xsi:type="xsd:short">314</myShortT>
      <myCharT xsi:type="xsd:char">P</myCharT>
    </complexType>
  </DPSerialization>
  
-----------------------------------------------------------------------

Assuming you are in the directory containing this README:

## To clean:
ant -buildfile genericCheckpointing/src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile genericCheckpointing/src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
ant -buildfile genericCheckpointing/src/build.xml run -Darg0=FirstArg -Darg1=SECOND -Darg2=THIRD

From the command line take two three arguments: (1) mode, (2) N and (3) output file name. 
The mode could be "serdeser" or "deser".  
In the "serdeser" mode, N referes to the NUM_OF_OBJECTS of MyAllTypesFirst and MyAllTypesSecond each. 
In the "deser" mode, N refers to the number of objects to be deserialized.
