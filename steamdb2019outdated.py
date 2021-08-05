from bs4 import BeautifulSoup#, SoupStrainer
import time
import grequests

def f_parse_steam_profile(html_soup_steamprofile): #Pega o game "ID" da lista de desejos no steam profile
	lista_ids = []
	for game_id in html_soup_steamprofile.findAll('div',{'class' : 'wishlistRow'}):
		try:
			lista_ids.append(game_id.get('id').replace('game_',''))
		except:
			print ('problema no game id:',game_id)
	return lista_ids

def f_lista_steamdb_profile(game_ids): # Monta lista com as URLS steamdb + lista_game_ids 
	lista_ids = []
	[lista_ids.append("https://steamdb.info/app/" + i) for i in game_ids]
	return lista_ids
	
def f_bsoup_htmls(htmls): # formata htmls em BeautifulSoup para izi Parsing
	bsoup_html = []
	if type(htmls) is str:
		um_html = BeautifulSoup(htmls,'lxml')
		return um_html
	else:
		[bsoup_html.append(BeautifulSoup(i,'lxml')) for i in htmls]
		return bsoup_html 
	
def f_parse_steamdb (html_soup_steamdb): #Encontra Nome, preço e desconto no HTML STEAMDB
	listadelista = [] #[[Nomejogo,Preço,PreçoDesconto],[...],...]
	for i in html_soup_steamdb:
		lista_nomes = i.find('td',{'itemprop' : 'name'}).getText()
		try:
			var1 = i.find('td',{'class' : 'price-line', 'data-cc' :'br'})				
			if var1 is not None: # F2P nao econtra o caminho, NoneType
				preco = var1.findNext('td').getText()
				desconto = var1.findNext('td').findNext('td').findNext('td').findNext('td').getText()
				listadelista.append([lista_nomes,preco,desconto.replace('at','com')])
			else:
				listadelista.append([lista_nomes,'F2P','F2P'])
		except:
			print('problema no parsing steamdb:',lista_nomes)
	return listadelista

#TODO tratar url profile, pode ser ID ou NOME ou PRIVADO	
ids = '76561197964503974' 
a = time.time()
url_steam_profile = [grequests.get('https://steamcommunity.com/profiles/'+ ids +'/wishlist/')] #grequests.get  ASYNC object - >Tem q ser lista<
html_steam_profile_gre = grequests.map(url_steam_profile) 									   #grequests.map  HTTP response
html_map_steam_profile = html_steam_profile_gre[0].text   									   #htmls puro [0]
soup_steam_profile = f_bsoup_htmls(html_map_steam_profile)									   #html beautifulSoup formatado para PARSING
lista_game_ids = f_parse_steam_profile(soup_steam_profile)									   #html parseado
print(lista_game_ids)
print("get Steam Profile--- %s seconds ---" % (round(time.time() - a, 2)))


# SteamDB
urls_steamdb = f_lista_steamdb_profile(lista_game_ids) # lista com urls dos jogos na wishlist	

urls_steamdb_gre = []   # grequests.get  ASYNC object
steamdb_gre = []        # grequests.map  HTTP response
htmls_steamdb_gre = []  # htmls puro
bsoup_steamdb_html = [] # magia soup htmls beautifulSoup formatados para PARSING

a=time.time()
[urls_steamdb_gre.append(grequests.get(i)) for i in urls_steamdb]
steamdb_gre = grequests.map(urls_steamdb_gre) # grequests.map retorna lista ASYNC (html response)  --- urllib python é sync por isso demora?
[htmls_steamdb_gre.append(i.text) for i in steamdb_gre]
print("get SteamDB--- %s seconds ---" % (round(time.time() - a, 2)))

a=time.time()
bsoup_steamdb_html = f_bsoup_htmls(htmls_steamdb_gre)
print("get Soup SteamDB--- %s seconds ---" % (round(time.time() - a, 2)))
pomba = f_parse_steamdb(bsoup_steamdb_html)
for i in pomba:
	print(i)

