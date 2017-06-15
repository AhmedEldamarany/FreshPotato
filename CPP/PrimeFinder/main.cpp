#include <iostream>

using namespace std;
bool isPrime(int number);
int main()
{
int limit;
    cout << "Enter Your Upper limit" << endl;
    cin>>limit;
    for(int i=2;i<=limit;i++)
    if(isPrime(i)) cout<<i<<endl;
    return 0;
}

bool isPrime(int number){
if(number%2==0&&number!=2) return false;
for(int i=3;i<number;i+=2)
if(number%i==0) return false;
return true;

}
