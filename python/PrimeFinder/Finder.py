
def isPrime(number):
    if number!=2 and number%2==0 :
        return False
    for i in range(3,number):
        if number%i==0:
            return False
    return True

limit=int( input("Enter Your upper limit "))
for  i in range(2,limit):
    if isPrime(i):
        print(i)
