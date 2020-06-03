from collections import Counter
import itertools
import random

def score(p, q):
    hits = sum(p_i == q_i for p_i, q_i in zip(p, q))
    misses = sum((Counter(p) & Counter(q)).values()) - hits
    return hits, misses

code = 'RWWB'
possibles = list(itertools.product('ROYGBW', repeat=4))
while len(possibles) > 1:
   guess = random.choice(possibles)
   result = score(code, guess)
   print(guess, result)
   possibles = [p for p in possibles if score(p, guess) == result]
