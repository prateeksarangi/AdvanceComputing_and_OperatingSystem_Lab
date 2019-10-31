#include<iostream> 
#include<math.h>
using namespace std;
 
long long int power(long long int a, long long int b, long long int P) 
{ 
	if (b == 1) 
		return a; 

	else
		return (((long long int)pow(a, b)) % P); 
} 

int main() 
{ 
	long long int n, g, x, A, y, B, ka, kb; 
	cout<<"Enter the value of n:";
	cin>>n; 
 	cout<<"Enter the value of g:";
 	cin>>g;
	cout<<"Enter the random number for A:" ;
	cin>>x;
	cout<<"Enter the random number for B:" ;
	cin>>y;
	
	A = power(g, x, n);  
	B = power(g, y, n); 
	
	cout<<"The value of n:"<<n<<endl;
	cout<<"The value of g:"<<g<<endl; 
	cout<<"the private key of A is:"<<x<<endl;
	cout<<"the private key of B is:"<<y<<endl;
	ka = power(B, x, n); 
	kb = power(A, y, n); 
	
	cout<<"Secret key for the A is :"<<ka<<endl; 
	cout<<"Secret Key for the B is :"<< kb; 
	
	return 0; 
} 

