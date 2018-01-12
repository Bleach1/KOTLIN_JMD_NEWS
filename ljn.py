print('%2d-%02d' % (3, 1))  # 3-01  2d占位
print('%.2f' % 3.1415926)  # 3.14
print('growth rate: %d %%' % 7)  # %%=%


def sumX(nums):
    x = 0
    for n in nums:
        x = x + n * n
    return x


print(sumX((1, 2, 3)))
nums = [1, 2, 3]


def calc(*numbers):
    sum = 0
    for n in numbers:
        sum = sum + n * n
    return sum


# 可变参数直接调用

print(calc(1, 2, 3))
print(calc(*nums))


# 关键字参数
def person(name, age, **kw):
    print('name:', name, 'age:', age, 'other:', kw)


person('Michael', 30)
person('Bob', 35, city='Beijing')
extra = {'city': 'Beijing', 'job': 'Engineer'}
person('Jack', 24, **extra)


# 命名关键字参数
def person(name, age, *, city, job):
    print(name, age, city, job)


person('Jack', 24, city='Beijing', job='Engineer')

L = [x * x for x in range(10)]  # 列表生成式
g = (x * x for x in range(10))  # 生成器
