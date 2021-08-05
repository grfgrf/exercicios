
import time
import grequests
import re
# import datetime
def buscaIdsWishlistSteam(): #TODO aceitar profiles como parametro e checkar se existem ou são privados

    url_steam_profile = [grequests.get('https://steamcommunity.com/profiles/76561198119500990/wishlist')] #grequests.get  ASYNC object - >Tem q ser lista<
    html_steam_profile_gre = grequests.map(url_steam_profile)                      #grequests.map  HTTP response
    
   
    padraoRegexID = '(?<="appid":)\d+'
    listaGameIds = re.findall(padraoRegexID, html_steam_profile_gre[0].text )

    return listaGameIds    

def f_listaLinksSteamDBGames(game_ids): # Monta lista com as URLS steamdb + lista_game_ids 
	listaLinks = []
	[listaLinks.append("https://steamdb.info/app/" + i) for i in game_ids]
   # [listaLinks.append("http://webcache.googleusercontent.com/search?q=cache:https://steamdb.info/app/" + i) for i in game_ids]
    
	return listaLinks    

def f_menorPrecoDoJogoEver(lista_linksmontados):
    resultado = []
 
    for jogosId in linksSteamDB:
        
        
        headers = {'User-Agent': 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:80.0) Gecko/20100101 Firefox/80.0'}

        #headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.76 Safari/537.36','referer':'https://www.google.com/'}    
        
        url_steamDB_profile = [grequests.get(jogosId , headers=headers)] #grequests.get  ASYNC object - >Tem q ser lista<
        html_steamDB_profile_gre = grequests.map(url_steamDB_profile)

        print('HTTP STATUS')
        print(html_steamDB_profile_gre)
        # padraoRegexNome ='(?<=\<h1 itemprop="name"\>)[\d|\w|\W|\s]+?\<'
        padraoRegexPreco = 'R\$\s[\S]+\sat[\s|\S]+?\%'
        maiorDesconto = (re.findall(padraoRegexPreco, html_steamDB_profile_gre[0].text ))
        resultado.append(maiorDesconto[0][0:maiorDesconto[0].find('at')])
        time.sleep(0.1)
    return resultado



tempoExecucao = time.time()
#busca games IDs na wishlist steamcommunity
print("jogos da wishlist do profile")
print(buscaIdsWishlistSteam())
print("get Steam Profile--- %s seconds ---" % (round(time.time() - tempoExecucao, 2)))

#menor preço
linksSteamDB = f_listaLinksSteamDBGames(buscaIdsWishlistSteam())

print(f_menorPrecoDoJogoEver(linksSteamDB))
print(" ^ preço mínimo que chegaram")
