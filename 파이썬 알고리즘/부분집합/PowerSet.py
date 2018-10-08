
# coding: utf-8

# In[12]:


import copy


# In[37]:


list=[0,0,0,0,0]


# In[38]:


powerSetList=[set({})]


# In[39]:


for i in range(len(list)):
    count = len(powerSetList)
    for j in range(0,count):
        tempSet=copy.deepcopy(powerSetList[j])
        tempSet.add(list[i])
        powerSetList.append(tempSet)


# In[40]:


print(len(powerSetList))
print(powerSetList)

