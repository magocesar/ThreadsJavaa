import threading
import time
import itertools

achouSenha = threading.Event()
policialChegou = threading.Event()

def tentarSenha(policialChegou, achouSenha, senha):
    for i in itertools.count():
        if policialChegou.is_set():
            break
        if achouSenha.is_set():
            break
        if i == senha:
            print("Ladrão achou senha!")
            print("Achei a senha: ", i)
            achouSenha.set()
            break

def policial(achouSenha):
    for i in range(10):
        if achouSenha.is_set():
            return
        print("Policial chegando em: ", 10 - i, " segundos")
        time.sleep(1)
    print("Policial chegou")
    policialChegou.set()

senha = 788123

listaLadroes = []
for i in range(2):
    l = threading.Thread(target=tentarSenha, args=(policialChegou, achouSenha, senha))
    listaLadroes.append(l)
for l in listaLadroes:
    l.start()

policial(achouSenha)


