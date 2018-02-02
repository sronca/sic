Il codice originale è nel git: http://git.devmiur.com/git/template-html.git

- non modificare mai i file css e javascript forniti con il template

- ogni nuovo progetto che utilizza il template può aggiungere un proprio file css con cui eventualmente sovrascrivere le regole di bootstrap.css o di style.css. il file css specifico del progetto deve chiamarsi "custom.css" e va dentro la cartella "assets/css". il file "custom.css" va sempre aggiunto come ultimo dopo "style.css"

- ogni nuovo progetto che utilizza il template può aggiungere un proprio file js con funzioni utili al progetto stesso. il file deve chiamarsi "custom.lib.js" e va dentro la cartella "assets/js". il file "custom.lib.js" va sempre dopo "functions.lib.min.js"

- i plugin javascript vanno sempre dentro la cartella plugins. il nome della cartella de plugin deve essere di questo tipo (non considerare le []):

jq-[nomeplugin] se si tratta di un plugin jquery
bt-[nomeplugin] se si tratta di un plugin bootstrap
[nomeplugin] se si tratta di un plugin generico vanilla javascript (js puro)

- "miur-icons" è il file iconfont generale per il template. se si ha bisogno di nuove icone aggiungere un nuovo file icons

- la sidebar laterale gestisce fino a 5 livelli. non si può (e non si deve andare oltre)

- il div che racchiude il contenuto è il seguente:

<div id="main-page-content" class="page-content page-sidebar clearfix">

CODICE PAGINA

</div>

dentro questo div si inserisce il contenuto specifico del progetto. se il contenuto è puramente testuale è necessario inserire un ulteriore div che wrappa il testo per evitare che la linea sia troppo lunga. ecco un esempio:

<div id="main-page-content" class="page-content page-sidebar clearfix">

<h2>Titolo</h2>

<div class="wrapper-content">
contenuto testuale
</div>

</div>

il div con classe "wrapper-content" può essere "duplicato" quante volte volete in pagina. ad esempio:

<div id="main-page-content" class="page-content page-sidebar clearfix">

<h2>Titolo</h2>

<div class="wrapper-content">
contenuto testuale
</div>

<div> contenuto non testuale </div>

<div class="wrapper-content">
contenuto testuale
</div>

</div>