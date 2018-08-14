from random import randint

list1 = []
list2 = []
list3 = []
list4 = []
list5 = []
order = []
index = []

i=0
num = int(input("No of attributes: "))

while(i<num):
    templist = input("Enter data for attribute %d: " %(i+1)).split()
    if i==0:
        list1 = templist
        templist = []
    elif i==1:
        list2 = templist
        templist = []
    elif i==2:
        list3 = templist
        templist = []
    elif i==3:
        list4 = templist
        templist = []
    elif i==4:
        list5 = templist
        templist = []
    i+=1

inst = int(input("Enter no of instances: "))

print("\n\n@data")
print("%")

while(inst>0):
    i=0
    rand =0
    temp1=0
    while(i<num):
        if i==0:
            temp1 = randint(0, (len(list1)-1))
            index.append(temp1)
            rand = ((rand*10) + temp1)
        elif i==1:
            temp1 = randint(0, (len(list2)-1))                     
            index.append(temp1)
            rand = ((rand*10) + temp1)
        elif i==2:
            temp1 = randint(0, (len(list3)-1))                     
            index.append(temp1)
            rand = ((rand*10) + temp1)
        elif i==3:
            temp1 = randint(0, (len(list4)-1))                     
            index.append(temp1)
            rand = ((rand*10) + temp1)
        elif i==4:
            temp1 = randint(0, (len(list5)-1))
            index.append(temp1)
            rand = ((rand*10) + temp1)
        i+=1  

    
    if not rand in order:
        inst-=1
        order.append(rand)
        j=0
        #print("Lenght: ", len(index))
        for digit in index:
            temp = int(digit)
            j+=1
            if j==1:
                print(list1[int(temp)], end='')
            elif j==2:
                print(list2[int(temp)], end='')
            elif j==3:
                print(list3[int(temp)], end='')
            elif j==4:
                print(list4[int(temp)], end='')
            elif j==5:
                print(list5[int(temp)], end='')
            if j<num:
                print(", ", end='')

        index.clear()
        print()
        
#print("\n\nOrder: ", order)
print("%")


"""
India China Japan USA UK Germany
Dawn Morning Afternoon Evening Dusk Night
Work Sleep School Eat Travel College Play
Kids Todlers Adults Teens Seniors
0 10 20 30 40 50 60 70 80 90 100
"""
