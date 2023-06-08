import random
import threading
import time

class Main:    
    def __init__(self):
        cofre = Cofre()
        senhaEncontrada = threading.Event()
        policialChegou = threading.Event()

        l1 = Ladrao(0, 49999, cofre, senhaEncontrada, policialChegou)
        l2 = Ladrao(99999, 50000, cofre, senhaEncontrada, policialChegou)
        p1 = Policial(10, senhaEncontrada, policialChegou)

        Threadl1 =  threading.Thread(target=l1.run)
        Threadl2 =  threading.Thread(target=l2.run)
        Threadp1 =  threading.Thread(target=p1.run)

        Threadl1.start()
        Threadl2.start()
        Threadp1.start()

        Threadl1.join()
        Threadl2.join()
        Threadp1.join()

        print("\nA senha é: ", cofre.getSenha(), "\n")

class Cofre:
    def __init__(self):
        self.senha = random.randint(0, 99999)

    def abrir(self, senha):
        if(self.senha == senha):
            return True
        else:
            return False

    def getSenha(self):
        return self.senha
    
class Ladrao:
    def __init__(self, start, end, cofre, senhaEncontrada, policialChegou):
        self.start = start
        self.end = end
        self.cofre = cofre
        self.senhaEncontrada = senhaEncontrada
        self.policialChegou = policialChegou
    
    def run(self):
        if(self.start < self.end):
            self.crescente()
        else:
            self.decrescente()
        
    def crescente(self):
        while not self.senhaEncontrada.is_set() and not self.policialChegou.is_set():
            for i in range(self.start, self.end):
                if self.senhaEncontrada.is_set() or self.policialChegou.is_set():
                    break
                print("Ladrão tentando senha: ", i)
                if self.cofre.abrir(i):
                    self.senhaEncontrada.set()
                    time.sleep(1)
                    print(f"\nLadrão {threading.current_thread().name} encontrou a senha: ")
                    break

        if(not self.senhaEncontrada.is_set() and self.policialChegou.is_set()):
            print(f"\nLadrão {threading.current_thread().name} foi pego pelo policial")
            return False

        print(f"\nLadrão {threading.current_thread().name} terminou de procurar a senha")
        return False
        
    def decrescente(self):
        while not self.senhaEncontrada.is_set() and not self.policialChegou.is_set():
            for i in range(self.start, self.end, -1):
                if self.senhaEncontrada.is_set() or self.policialChegou.is_set():
                    break
                print("Ladrão tentando senha: ", i)
                if self.cofre.abrir(i):
                    self.enhaEncontrada.set()
                    time.sleep(1)
                    print("\nLadrão encontrou a senha...")
                    break

        if(not self.senhaEncontrada.is_set() and self.policialChegou.is_set()):
            print(f"\nLadrão {threading.current_thread().name} foi pego pelo policial")
            return False

        print(f"\nLadrão {threading.current_thread().name} terminou de procurar a senha")
        return False
        
class Policial:
    def __init__(self, tempoResposta, senhaEncontrada, policialChegou):
        self.tempoResposta = tempoResposta
        self.senhaEncontrada = senhaEncontrada
        self.policialChegou = policialChegou
        
    def run(self):
        for i in range(self.tempoResposta):
            if(self.senhaEncontrada.is_set()):
                return False
            print(f'\nPolicial chegando em {10 - i} segundos...\n')
            time.sleep(1)
        
        print("\nPolicial chegou...\n")
        self.policialChegou.set()
        return True
    
if __name__ == "__main__":
    Main()