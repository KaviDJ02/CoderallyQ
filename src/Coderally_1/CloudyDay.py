import math
import os
import random
import re
import sys
def maximumPeople(p, x, y, r):

    n = len(x)
    m = len(y)
    results = []
    for i in range(n):
        results.append((x[i], 1, i))
    for i in range(m):
        results.append((y[i]-r[i], 0, i))
    for i in range(m):
        results.append((y[i]+r[i], 2, i))
    results.sort()
    cloud_set = set()

    gain = [0 for _ in range(m)]
    base = 0

    for x, tp, ID in results:
        if tp == 0:
            cloud_set.add(ID)
        if tp == 1:
            if len(cloud_set) == 1:
                gain[next(iter(cloud_set))] += p[ID]
            if len(cloud_set) == 0:
                base += p[ID]
        if tp == 2:
            cloud_set.remove(ID)
    return base + max(gain)

# Complete the maximumPeople function below.
#def maximumPeople(p, x, y, r):
    # Return the maximum number of people that will be in a sunny town after removing exactly one cloud.

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input())

    p = list(map(int, input().rstrip().split()))

    x = list(map(int, input().rstrip().split()))

    m = int(input())

    y = list(map(int, input().rstrip().split()))

    r = list(map(int, input().rstrip().split()))

    result = maximumPeople(p, x, y, r)

    fptr.write(str(result) + '\n')

    fptr.close()