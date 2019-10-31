from __future__ import print_function

print( "Publicly Shared Variables:")
sharedPrime = int(input( "    Enter the value of 'n': "))
sharedBase = int(input( "    Enter the value of 'g':  "))
aliceSecret = int(input("Secret message of Alice: "))
bobSecret = int(input("Secret message of Bob: "))
 
A = (sharedBase**aliceSecret) % sharedPrime
print( "\n  Alice Sends Over Public Chanel: " , A )
 
B = (sharedBase ** bobSecret) % sharedPrime
print( "  Bob Sends Over Public Chanel: ", B )
 
print( "\n------------------------\n" )
print( "Privately Calculated Shared Secret:" )

aliceSharedSecret = (B ** aliceSecret) % sharedPrime
print( "    Alice Shared Secret: ", aliceSharedSecret )
 
bobSharedSecret = (A**bobSecret) % sharedPrime
print( "    Bob Shared Secret: ", bobSharedSecret )
