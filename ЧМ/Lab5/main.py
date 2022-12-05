import matplotlib.pyplot as plt
import numpy
import numpy as np
from scipy.interpolate import CubicSpline


def f(x):
    return x ** 3 + 2 * x ** 2 - 3 * x + 1


def LagrangePolinome(xn, yi, i):
    result = np.poly1d([yi])
    for j in range(len(xn)):
        if i != j:
            result *= np.poly1d([1, -xn[j]]) / (xn[i] - xn[j])
    return result


def Lagrange(xn, yn):
    lagrange = np.poly1d([0])
    for i in range(len(xn)):
        lagrange += LagrangePolinome(xn, yn[i], i)
    return lagrange


def NewtonPolinome(split_diff, xn, k):
    result = np.poly1d([split_diff])
    for i in range(0, k):
        result *= np.poly1d([1, -xn[i]])
    return result


def calculate_split_i(p, xn, fx):
    i = 0
    j = 0
    result = np.zeros(len(xn) - p, dtype=float)
    while (i + p) < len(xn):
        result[j] = (fx[i + 1] - fx[i]) / (xn[i + p] - xn[i])
        j += 1
        i += 1
    return result


def Newton(xn, yn):
    split_diff = []
    for i in range(0, len(xn)):
        if i == 0:
            split_diff.append(yn)
            continue
        split_diff.append(calculate_split_i(i, xn, split_diff[i - 1]))

    newton = np.poly1d([split_diff[0][0]])
    for i in range(1, len(split_diff)):
        newton += NewtonPolinome(split_diff[i][0], xn, i)
    return newton


def generate_yn(xn):
    y = []
    for xi in xn:
        y.append(f(xi))
    return y


if __name__ == '__main__':
    xn = [0, 2, 3, 4]
    yn = generate_yn(xn)

    x = np.asfarray(xn)
    y = np.asfarray(yn)
    f = CubicSpline(x, y, bc_type='natural')

    lagr = Lagrange(xn, yn)
    newt = Newton(xn, yn)
    print(lagr)
    print(newt)
    xn = np.linspace(0, 4, 100)

    # lagrange
    plt.plot(xn, [numpy.polyval(lagr, x) for x in xn], 'b-')
    # newton
    plt.plot(xn, [numpy.polyval(newt, x) for x in xn], 'g-')
    # cubic spline
    plt.plot(xn, f(xn), "r")
    plt.show()
