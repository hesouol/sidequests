from datetime import datetime
import sys
from collections import namedtuple


Ponto = namedtuple('Ponto', ['dia', 'batidas', 'horas_trab'])
Batida = namedtuple("Batida", ['tipo', 'hora'])
pontos = []


def main():
    now = datetime.now()
    dia = now.date()

    ponto_hoje = None

    for ponto in pontos:
        if ponto.dia == dia:
            ponto_hoje = ponto

    if len(sys.argv) == 1:
        hora = now.time()
        tipo = 'e'
        if ponto_hoje:
            if ponto_hoje.batidas[-1].tipo == 'e':
                tipo = 's'
    else:
        hora = datetime.strptime(sys.argv[2], "%H:%M").time()
        tipo = sys.argv[1]

    if ponto_hoje:
        ponto_hoje.batidas.append(Batida(tipo, hora))
    else:
        pontos.append(Ponto(dia, [Batida(tipo, hora)], 0))

    print(pontos)

if __name__ == '__main__':
    main()
    main()