package com.example.init;

import com.example.entity.Article;
import com.example.entity.Author;
import com.example.service.ArticleService;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class DataInitializer {

    @Inject
    ArticleService articleService;

    @Transactional
    public void onStart(@Observes StartupEvent ev) {
        if (!articleService.getAllArticles().isEmpty()) {
            return;
        }

        // Initialize Authors
        Author a1 = new Author("a1", "Ioana Moldovan", Map.of(
                "ro", "Editor-șef",
                "en", "Editor-in-Chief",
                "hu", "Főszerkesztő",
                "de", "Chefredakteurin",
                "fr", "Rédactrice en chef"
        ), null);

        Author a2 = new Author("a2", "Andrei Popescu", Map.of(
                "ro", "Corespondent",
                "en", "Correspondent",
                "hu", "Tudósító",
                "de", "Korrespondent",
                "fr", "Correspondant"
        ), null);

        Author a3 = new Author("a3", "Maria Ionescu", Map.of(
                "ro", "Redactor Cultură",
                "en", "Culture Editor",
                "hu", "Kultúra-szerkesztő",
                "de", "Kulturredakteurin",
                "fr", "Rédactrice Culture"
        ), null);

        Author a4 = new Author("a4", "Bogdan Dumitrescu", Map.of(
                "ro", "Redactor Sport",
                "en", "Sports Editor",
                "hu", "Sporttudósító",
                "de", "Sportredakteur",
                "fr", "Rédacteur Sport"
        ), null);

        Author a5 = new Author("a5", "Dr. Elena Radu", Map.of(
                "ro", "Colaborator",
                "en", "Contributor",
                "hu", "Közreműködő",
                "de", "Mitarbeiterin",
                "fr", "Collaboratrice"
        ), null);

        // Article 1
        Article art1 = new Article();
        art1.setId("1");
        art1.setTitle(Map.of(
                "ro", "Nopți de Sesiune în Era ChatGPT: Între Trișat și Progres",
                "en", "Exam Season in the ChatGPT Era: Between Cheating and Progress",
                "hu", "Vizsgaidőszak a ChatGPT korában: Csalás vagy fejlődés?",
                "de", "Prüfungszeit im ChatGPT-Zeitalter: Zwischen Betrug und Fortschritt",
                "fr", "Session d'examens à l'ère ChatGPT : Entre triche et progrès"
        ));
        art1.setSummary(Map.of(
                "ro", "Cum schimbă uneltele AI modul în care studenții se pregătesc pentru examene și ce spun cadrele didactice.",
                "en", "How AI tools are changing the way students prepare for exams and what faculty has to say about it.",
                "hu", "Hogyan változtatják meg az AI-eszközök a vizsgafelkészülést, és mit mondanak erről az oktatók.",
                "de", "Wie KI-Tools die Prüfungsvorbereitung verändern und was die Lehrkräfte dazu sagen.",
                "fr", "Comment les outils d'IA transforment la préparation aux examens et ce qu'en pensent les enseignants."
        ));
        art1.setContent("""
                Sesiunea de iarnă bate la ușă și, odată cu ea, o întrebare care împarte corpul didactic al universității noastre în două tabere ireconciliabile: este inteligența artificială un instrument de învățare sau cel mai sofisticat sistem de trișat inventat vreodată?
                
                În biblioteca facultății, la ora unsprezece noaptea, Mara Georgescu, studentă în anul al treilea la Informatică, tastează febril într-o fereastră de browser. Nu este un referat, nu este un cod. Este o conversație cu ChatGPT, din care încearcă să înțeleagă teorema lui Bayes înainte de examenul de dimineață. „Nu îl folosesc să scriu pentru mine," spune ea fără să ridice privirea de la ecran. „Îl folosesc ca pe un tutore care nu se supără că îl întreb de zece ori același lucru."
                
                Profesorul Octavian Drăghici, titular al cursului de Probabilități și Statistică, are o perspectivă diferită. A implementat acest semestru un sistem de evaluare orală pentru jumătate din notă, tocmai pentru a contracara ceea ce el numește „inflația artificială de competențe". Rezultatul a fost neașteptat: rata de promovare a crescut cu doisprezece procente. „Studenții au venit mai pregătiți la oral," recunoaște el, cu o urmă de surpriză în glas. „Nu știu dacă e meritul lor sau al mașinăriei, dar știu că au înțeles materia."
                
                Dezbaterea nu este una simplă. Universitatea nu are încă o politică oficială privind utilizarea AI în elaborarea lucrărilor. Fiecare cadru didactic stabilește propriile reguli, ceea ce duce la o situație paradoxală: același student poate folosi liber ChatGPT la un curs și poate fi exmatriculat pentru același lucru la altul. Senatul universitar a anunțat că va dezbate un regulament în primăvara acestui an, dar studenții nu sunt convinși că vocea lor va fi auzită în mod real.
                
                „Problema nu este dacă folosești AI sau nu," spune Alexandru Ionescu, vicepreședinte al asociației studențești. „Problema este că nimeni nu ne pregătește să îl folosim bine. Nicio facultate din România nu are un curs de 'cum să colaborezi cu o inteligență artificială fără să îți atrofiezi gândirea critică'. Și asta e o lacună enormă."
                
                Deocamdată, biblioteca rămâne deschisă până la miezul nopții, uneltele AI funcționează la capacitate maximă, iar sesiunea continuă. Răspunsul, ca de obicei, va veni cu întârziere.""");
        art1.setAuthor(a1);
        art1.setCategory("Campus");
        art1.setTags(Map.of(
                "ro", List.of("inteligență artificială", "sesiune", "educație", "tehnologie"),
                "en", List.of("artificial intelligence", "exams", "education", "technology"),
                "hu", List.of("mesterséges intelligencia", "vizsgaidőszak", "oktatás", "technológia"),
                "de", List.of("künstliche Inteligenz", "Prüfungen", "Bildung", "Tehnologie"),
                "fr", List.of("intelligence artificielle", "examens", "éducation", "technologie")
        ));
        art1.setPublishedAt(Instant.parse("2026-06-10T09:00:00Z"));
        art1.setImageUrl("/src/assets/World Cup Images.jpeg");
        art1.setReadTimeMinutes(5);
        articleService.saveArticle(art1);

        // Article 2
        Article art2 = new Article();
        art2.setId("2");
        art2.setTitle(Map.of(
                "ro", "Greva Climatică la Universitate: Studenții Cer Acțiune Concretă",
                "en", "Climate Strike at University: Students Demand Concrete Action",
                "hu", "Klímasztrájk az egyetemen: A diákok konkrét lépéseket követelnek",
                "de", "Klimastreik an der Universität: Studierende fordern konkretes Handeln",
                "fr", "Grève climatique à l'université : Les étudiants exigent des mesures concrètes"
        ));
        art2.setSummary(Map.of(
                "ro", "Sute de studenți au protestat în curtea universității, cerând eliminarea combustibililor fosili din portofoliul de investiții al instituției.",
                "en", "Hundreds of students protested in the university yard, demanding the removal of fossil fuels from the institution's investment portfolio.",
                "hu", "Több száz diák tüntetett az egyetem udvarán, követelve a fosszilis tüzelőanyagok kivonását az intézmény befektetési portfóliójából.",
                "de", "Hunderte Studierende protestierten im Universitätshof und forderten den Ausschluss fossiler Brennstoffe aus dem Investitionsportfolio.",
                "fr", "Des centaines d'étudiants ont manifesté dans la cour de l'université, exigeant l'exclusion des combustibles fossiles du portefeuille d'investissement."
        ));
        art2.setContent("""
                Vineri, la prânz, curtea universității s-a umplut de pancarte și scandări. Aproximativ patru sute de studenți și câțiva profesori s-au adunat sub titlul generic „Vinerea pentru Viitor", în cadrul unui val global de proteste climatice. Mesajul central, repetat pe zeci de pancarte, era adresat direct rectorului: „Dezinvestiți din combustibili fosili."
                
                Mișcarea a luat amploare după ce o investigație publicată în numărul trecut al revistei noastre a dezvăluit că fondul de pensii al universității deține acțiuni în valoare de peste trei milioane de euro la companii din sectorul petrolier și gazier. Administrația a răspuns printr-un comunicat în care se precizează că „toate investițiile respectă cadrul legal în vigoare" — un răspuns pe care organizatorii protestului l-au considerat insuficient.
                
                „Nu vorbim de ilegalitate, vorbim de responsabilitate morală," a declarat Simona Petre, studentă la Geografie și una dintre coordonatoarele acțiunii. „O instituție de educație care se hrănește financiar din industria care distruge planeta pe care o studiem este o contradicție pe care nu o mai putem accepta."
                
                Protestul a decurs pașnic, cu intervenția poliției comunitare doar pentru a asigura circulația. La finalul acțiunii, o delegație de cinci studenți a fost primită de prorectorul pentru relații cu studenții, care a promis că subiectul va fi pus pe agenda ședinței de consiliu din luna iulie. Organizatorii au anunțat că vor reveni săptămânal până când vor exista angajamente concrete și un calendar de implementare.
                
                Reacțiile din rândul studenților care nu au participat au fost mixte. „Înțeleg mesajul, dar nu cred că universitatea noastră are suficientă greutate pentru a schimba piețele financiare globale," a spus un student de la Economie care a preferat să rămână anonim. Altul, de la Drept, a subliniat că exact această mentalitate a lăsat problema climatică nerezolvată timp de decenii: „Fiecare actor individual crede că e prea mic. Acesta este paradoxul acțiunii colective."
                
                Indiferent de rezultat, vinerea aceea a demonstrat că subiectele globale nu rămân în afara porților universității.""");
        art2.setAuthor(a2);
        art2.setCategory("Activism");
        art2.setTags(Map.of(
                "ro", List.of("climă", "protest", "campus", "mediu"),
                "en", List.of("climate", "protest", "campus", "environment"),
                "hu", List.of("klíma", "tüntetés", "kampusz", "környezet"),
                "de", List.of("Klima", "Protest", "Campus", "Umwelt"),
                "fr", List.of("climat", "protestation", "campus", "environnement")
        ));
        art2.setPublishedAt(Instant.parse("2026-06-07T14:30:00Z"));
        art2.setImageUrl("/src/assets/Ubb Photos.jpeg");
        art2.setReadTimeMinutes(4);
        articleService.saveArticle(art2);

        // Article 3
        Article art3 = new Article();
        art3.setId("3");
        art3.setTitle(Map.of(
                "ro", "Festivalul Internațional de Film Studențesc: O Fereastră Spre Lume",
                "en", "International Student Film Festival: A Window to the World",
                "hu", "Nemzetközi Diákfilmfesztivál: Ablak a világra",
                "de", "Internationales Studentenfilmfestival: Ein Fenster zur Welt",
                "fr", "Festival International du Film Étudiant : Une fenêtre sur le monde"
        ));
        art3.setSummary(Map.of(
                "ro", "Ediția a VIII-a a festivalului a adus peste o sută de scurtmetraje din patruzeci de țări, cu premiera mai multor producții românești remarcabile.",
                "en", "The 8th edition brought over a hundred short films from forty countries, including the premiere of several remarkable Romanian productions.",
                "hu", "A fesztivál 8. kiadása több mint száz rövidfilmet hozott negyven országból, köztük számos figyelemre méltó román produkció premierjével.",
                "de", "Die 8. Ausgabe brachte über hundert Kurzfilme aus vierzig Ländern, darunter die Premiere mehrerer bemerkenswerter rumänischer Produktionen.",
                "fr", "La 8e édition a réuni plus d'une centaine de courts-métrages de quarante pays, avec la première de plusieurs productions roumaines remarquables."
        ));
        art3.setContent("""
                Timp de o săptămână, amfiteatrul din corpul C al universității s-a transformat în sală de cinema. Festivalul Internațional de Film Studențesc, ajuns la a opta ediție, a proiectat 107 scurtmetraje din 40 de țări, atrăgând în jur de două mii de spectatori — record absolut pentru eveniment.
                
                Selecția din acest an a avut ca temă neoficială „granițele": granițe geografice, culturale, interioare. Un documentar iranian despre o familie de nomazi care refuză să se mute la oraș, un film de animație finlandez despre dislexie, o ficțiune scurtă colombiană despre un copil care descoperă că bunica lui a trăit o viață dublă. Diversitatea nu a fost niciodată mai vizibilă în programul festivalului.
                
                Dintre producțiile românești, cel mai mult a impresionat „Liniștea de după", un scurtmetraj în alb-negru realizat de trei studenți de la UNATC, care explorează relația dintre un tânăr și tatăl său bolnav. Pelicula a câștigat Premiul Special al Juriului și a primit o ovație la finalul proiecției — raritate pentru un film de douăzeci de minute care nu conține niciun moment de comedie sau acțiune.
                
                „Am vrut să facem un film în care tăcerea spune mai mult decât dialogul," a explicat Radu Vasile, regizorul de 22 de ani, vizibil emoționat la decernarea premiului. „Dacă oamenii au stat liniștiți douăzeci de minute și nu și-au scos telefonul, înseamnă că am reușit."
                
                Organizatorii au anunțat că din toamnă vor introduce și o secțiune de realitate virtuală, în parteneriat cu o companie de tehnologie locală. Festivalul, finanțat integral din cotizații studențești și sponsorizări private, rămâne unul dintre cele mai bine organizate evenimente culturale din viața universității — un argument solid pentru cei care susțin că studenții pot administra proiecte complexe la fel de eficient ca structurile profesionale.""");
        art3.setAuthor(a3);
        art3.setCategory("Cultură");
        art3.setTags(Map.of(
                "ro", List.of("film", "festival", "artă", "cinema"),
                "en", List.of("film", "festival", "art", "cinema"),
                "hu", List.of("film", "fesztivál", "művészet", "mozi"),
                "de", List.of("Film", "Festival", "Kunst", "Kino"),
                "fr", List.of("film", "festival", "art", "cinéma")
        ));
        art3.setPublishedAt(Instant.parse("2026-06-05T11:00:00Z"));
        art3.setUpdatedAt(Instant.parse("2026-06-06T08:00:00Z"));
        art3.setImageUrl("/src/assets/Ubb Photos.jpeg");
        art3.setReadTimeMinutes(4);
        articleService.saveArticle(art3);

        // Article 4
        Article art4 = new Article();
        art4.setId("4");
        art4.setTitle(Map.of(
                "ro", "Echipa de Fotbal a Universității Câștigă Campionatul Regional pentru Prima Dată în 14 Ani",
                "en", "University Football Team Wins Regional Championship for the First Time in 14 Years",
                "hu", "Az egyetem labdarúgócsapata 14 év után nyerte meg a regionális bajnokságot",
                "de", "Universitäts-Fußballteam gewinnt zum ersten Mal seit 14 Jahren die Regionalmeisterschaft",
                "fr", "L'équipe de football de l'université remporte le championnat régional pour la première fois en 14 ans"
        ));
        art4.setSummary(Map.of(
                "ro", "Victoria cu 3-1 în finala de duminică a pus capăt unui șir lung de dezamăgiri și a stârnit o explozie de bucurie în rândul studenților.",
                "en", "The 3-1 victory in Sunday's final ended a long series of disappointments and sparked an explosion of joy among students.",
                "hu", "A vasárnapi döntőben aratott 3-1-es győzelem véget vetett a hosszú csalódássorozatnak, și vărsat o explozie de bucurie în rândul studenților.",
                "de", "Der 3:1-Sieg im Sonntagsfinale beendete eine lange Serie von Enttäuschungen und löste eine Welle der Freude unter den Studierenden aus.",
                "fr", "La victoire 3-1 en finale dimanche a mis fin à une longue série de déceptions et déclenché une explosion de joie parmi les étudiants."
        ));
        art4.setContent("""
                Duminică, pe stadionul municipal, în fața a aproape trei mii de suporteri, echipa de fotbal a universității a câștigat campionatul regional universitar cu scorul de 3-1 în fața echipei Politehnicii. A fost primul titlu după paisprezece ani de așteptare — o perioadă lungă cât un ciclu complet de studenți, de la admitere la doctorat.
                
                Meciurile contând pentru campionat. Echipa antrenată din acest sezon de Adrian Munteanu, fost jucător profesionist reconvertit la fotbalul de amatori, a practicat un fotbal ofensiv care a adus opt victorii consecutive. Finalul a fost dramatic: golul de 2-1, marcat în minutul 78 de Cristi Lazăr, student în ultimul an la Inginerie Mecanică, a deschis drumul spre triumf.
                
                „Cristi ne-a salvat de câteva ori în turneul ăsta," a spus antrenorul Munteanu. „Dar cel mai important a fost spiritul de grup. Avem băieți din șase facultăți diferite. Au reușit să devină o echipă reală, nu doar o colecție de jucători."
                
                Victoria a fost sărbătorită cu o petrecere spontană în curtea căminului numărul 3, unde s-a cântat și s-a dansat până spre dimineață, spre supărarea portarului de noapte și spre deliciul tuturor celorlalți. Decanul Facultății de Educație Fizică și Sport a promis că va solicita alocarea unui buget mai mare pentru activitățile sportive în noul an universitar.
                
                Următoarea provocare a echipei este participarea la turneul național, programat în septembrie. Antrenorul a anunțat că va organiza probe de selecție în toamnă pentru a întări lotul. „Vrem să arătăm că nu a fost un accident," a spus el, zâmbind. „Vrem să câștigăm din nou."
                """);
        art4.setAuthor(a4);
        art4.setCategory("Sport");
        art4.setTags(Map.of(
                "ro", List.of("fotbal", "campionat", "sport", "victorie"),
                "en", List.of("football", "championship", "sport", "victory"),
                "hu", List.of("labdarúgás", "bajnokság", "sport", "győzelem"),
                "de", List.of("Fußball", "Meisterschaft", "Sport", "Sieg"),
                "fr", List.of("football", "championnat", "sport", "victoire")
        ));
        art4.setPublishedAt(Instant.parse("2026-06-03T17:00:00Z"));
        art4.setImageUrl("/src/assets/World Cup Images.jpeg");
        art4.setReadTimeMinutes(3);
        articleService.saveArticle(art4);

        // Article 5
        Article art5 = new Article();
        art5.setId("5");
        art5.setTitle(Map.of(
                "ro", "Sănătatea Mintală la Facultate: Cum Recunoaștem Semnele de Alertă",
                "en", "Mental Health at University: How to Recognize the Warning Signs",
                "hu", "Mentális egészség az egyetemen: Hogyan ismerjük fel a figyelmeztető jeleket?",
                "de", "Psychische Gesundheit an der Universität: Wie erkennen wir Warnsignale?",
                "fr", "Santé mentale à l'université : Comment reconnaître les signes d'alerte"
        ));
        art5.setSummary(Map.of(
                "ro", "Un psiholog din cadrul centrului de consiliere al universității explică de ce rata anxietății în rândul studenților a crescut cu 30% față de 2019.",
                "en", "A psychologist from the university's counseling center explains why the anxiety rate among students has increased by 30% since 2019.",
                "hu", "Az egyetem tanácsadó központjának pszichológusa elmagyarázza, miért nőtt 30%-kal a diákok körében a szorongás aránya 2019 óta.",
                "de", "Eine Psychologin des universitären Beratungszentrums erklärt, warum die Angstrate unter Studierenden seit 2019 um 30% gestiegen ist.",
                "fr", "Une psychologue du centre de conseil universitaire explique pourquoi le taux d'anxiété chez les étudiants a augmenté de 30% depuis 2019."
        ));
        art5.setContent("""
                Una din patru persoane aflate în prezent în sala de curs va trece, la un moment dat pe parcursul studiilor, printr-un episod de anxietate clinică sau depresie. Aceasta nu este o statistică dintr-un manual, ci realitatea documentată de centrul de consiliere psihologică al universității noastre, unde numărul consultațiilor s-a triplat în ultimii cinci ani.
                
                Dr. Elena Radu, psiholog clinician și coordonatoarea centrului, atribuie creșterea mai multor factori suprapuși: presiunea academică din ce în ce mai mare, incertitudinea economică de după pandemie, expunerea prelungită la rețele sociale și, paradoxal, o mai mare deschidere față de conceptul de sănătate mintală. „Oamenii vin mai ușor acum decât acum zece ani. Nu e că sunt mai bolnavi; e că nu mai le e rușine să ceară ajutor. Asta e o veste bună."
                
                Semnele de alertă sunt mai greu de recunoscut decât pare. Nu este vorba doar de tristețe sau panică vizibilă. Psihologul enumeră: dificultăți de concentrare persistente, insomnie sau, dimpotrivă, somnolență excesivă, retragere socială treptată, scăderea performanțelor academice fără o cauză aparentă, iritabilitate disproporționată. „Dacă observi la tine sau la un coleg aceste semne timp de mai mult de două săptămâni, merită să vorbești cu cineva."
                
                Universitatea oferă șase ședințe de consiliere gratuite pe an fiecărui student. Problema este că mulți nu știu că serviciul există sau cred că nu se aplică lor. „'Nu sunt atât de rău' este cel mai comun motiv pentru care oamenii nu vin," spune dr. Radu. „Dar sănătatea mintală nu funcționează ca un os rupt. Nu trebuie să fie complet fracturat ca să meriți atenție."
                
                Centrul de consiliere se află în clădirea administrativă, camera 12, și poate fi contactat la adresa de email afișată pe site-ul universității. Programările se fac fără să fie necesară o trimitere medicală. Dr. Radu subliniază că toate consultațiile sunt confidențiale și că niciun cadru didactic nu are acces la informațiile discutate acolo.""");
        art5.setAuthor(a5);
        art5.setCategory("Sănătate");
        art5.setTags(Map.of(
                "ro", List.of("sănătate mintală", "psihologie", "studenți", "consiliere"),
                "en", List.of("mental health", "psychology", "students", "counseling"),
                "hu", List.of("mentális egészség", "pszichológia", "diákok", "tanácsadás"),
                "de", List.of("psychische Gesundheit", "Psychologie", "Studierende", "Beratung"),
                "fr", List.of("santé mentale", "psychologie", "étudiants", "conseil")
        ));
        art5.setPublishedAt(Instant.parse("2026-05-28T10:00:00Z"));
        art5.setImageUrl("/src/assets/Ubb Photos.jpeg");
        art5.setReadTimeMinutes(5);
        articleService.saveArticle(art5);

        // Article 6
        Article art6 = new Article();
        art6.setId("6");
        art6.setTitle(Map.of(
                "ro", "Noua Conducere Studențească: Promisiuni Vechi, Față Nouă",
                "en", "New Student Leadership: Old Promises, New Face",
                "hu", "Új diákvezetés: Régi ígéretek, új arc",
                "de", "Neue Studierendenvertretung: Alte Versprechen, neues Gesicht",
                "fr", "Nouvelle direction étudiante : Vieilles promesses, nouveau visage"
        ));
        art6.setSummary(Map.of(
                "ro", "Interviu cu noul președinte al asociației studențești, ales cu 68% din voturi, despre planurile pentru un an universitar mai transparent.",
                "en", "Interview with the new president of the student association, elected with 68% of votes, on plans for a more transparent academic year.",
                "hu", "Interjú a diákszövetség új elnökével, akit a szavazatok 68%-ával választottak meg, az átláthatóbb tanév terveiről.",
                "de", "Interview mit dem neuen Präsidenten der Studierendenvertretung, der mit 68% der Stimmen gewählt wurde, über Pläne für ein transparenteres Studienjahr.",
                "fr", "Interview avec le nouveau président de l'association étudiante, élu avec 68% des voix, sur les projets pentru o an universitar transparent."
        ));
        art6.setContent("""
                Alexandru Ionescu a câștigat alegerile studențești cu un discurs neobișnuit de pragmatic pentru un candidat de douăzeci și unu de ani: a vorbit mai puțin despre ceea ce vrea să facă și mai mult despre ceea ce nu va promite că face. Paradoxul acesta i-a adus 68% din voturile exprimate și un mandat pe care intenționează să îl folosească diferit față de predecesorii săi.
                
                „Am observat că în fiecare an candidații promit aceleași lucruri: mai multe burse, mai bune cămine, mai multă transparență. Și în fiecare an se întâmplă mai puțin decât s-a promis. Am vrut să fiu cel care spune din start ce e posibil și ce nu depinde de noi," explică Alexandru, într-un birou al asociației studentești în care mobilierul pare să nu fi fost schimbat din anii '90.
                
                Pe lista de priorități concrete se află trei obiective: digitalizarea completă a secretariatelor studențești, un mecanism de feedback anonim pentru cadrele didactice și crearea unui fond de urgență pentru studenții cu dificultăți financiare severe. Pentru fiecare dintre ele există deja un plan de acțiune scris, cu termene și responsabilități asumate.
                
                Scepticii nu lipsesc. „Am auzit asta de atâtea ori," spune o studentă din anul doi, care preferă să nu fie citată. „Și nu s-a schimbat nimic. Administrația face ce vrea, și asociația studențească e mai mult decorativă." Alexandru recunoaște că structura de putere din universitate nu favorizează schimbarea rapidă, dar susține că tocmai de aceea transparența procesului contează mai mult decât rezultatele imediate.
                
                „Dacă la final de mandat am eșuat în toate cele trei obiective, vreau ca oricine să poată vedea exact unde, de ce și cine a blocat fiecare inițiativă. Responsabilitatea nu ar trebui să fie o virtute de unică folosință aplicată doar studenților la examene." Este o declarație care sună bine. Rămâne de văzut dacă va rezista contactului cu realitatea instituțională.""");
        art6.setAuthor(a1);
        art6.setCategory("Campus");
        art6.setTags(Map.of(
                "ro", List.of("asociație studențească", "conducere", "politică", "transparență"),
                "en", List.of("student association", "leadership", "politics", "transparency"),
                "hu", List.of("diákszövetség", "vezetés", "politika", "átláthatóság"),
                "de", List.of("Studierendenvereinigung", "Führung", "Politik", "Transparenz"),
                "fr", List.of("association étudiante", "direction", "politique", "transparence")
        ));
        art6.setPublishedAt(Instant.parse("2026-05-20T09:30:00Z"));
        art6.setImageUrl("/src/assets/World Cup Images.jpeg");
        art6.setReadTimeMinutes(4);
        articleService.saveArticle(art6);
    }
}
