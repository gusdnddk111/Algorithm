import copy

list=[0,0,0,0,0]
powerSetList=[set({})]

for i in range(len(list)):
    count = len(powerSetList)
    for j in range(0,count):
        tempSet=copy.deepcopy(powerSetList[j])
        tempSet.add(list[i])
        powerSetList.append(tempSet)

print(len(powerSetList))
print(powerSetList)
