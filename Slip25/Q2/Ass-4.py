import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
import math

df= pd.read_csv('Housing_2.csv') 
print(df.head())

mean_bedroom=math.floor(df['bedroom'].mean())
print(mean_bedroom)

df.bedroom=df.bedroom.fillna(mean_bedroom)
print(df)

reg = LinearRegression();

reg.fit(df[['area', 'bedroom','age']] ,df.price)
print(reg.coef_)
print(reg.intercept_)
reg.predict([[3000,3,40]])

plt.xlabel('area')
plt.ylabel('price')
plt.scatter(df.area,df.price,color='red',marker='+')
plt.plot(df.area,reg.predict(df[['area','bedroom','age']]),color='blue')
plt.show();
