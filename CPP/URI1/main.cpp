#include <iostream>

using namespace std;

int main()
{ //The Race of slugs
int input;


while(cin>>input){ int max=0;
int speed[input];
for(int i=0;i<input;i++){
cin>>speed[i];
if(max<i) max=i;
}
if(max<10)
cout<<1<<endl;
else if (max<20)
cout<<2<<endl;
else
cout<<3<<endl;




}




    return 0;
}
