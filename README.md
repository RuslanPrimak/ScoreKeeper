# ScoreKeeper
Training project of the Udacity "Android Basics: User Input" course. App allows to keep scoring in the American Football game.

This project has been created in order to practice with different visual controls which maintain User Input.
Moreover I have not restrained myself and have implement some other features in order to make app better.
In this app I have practiced with:
  1. Creating Layouts via editing xml.
  2. Binding listeners and views in the java sources.
  3. Using Fragments for equivalent parts of UI.
  4. Supporting Multiple Screens.
  5. Saving/restoring state of App.
  6. Using Styles.
  7. Debugging.
  8. Customizing ArrayAdapter for Spinner.
  9. Transitions.
  
I would especially like to note the last point besause as I have known the question "How to customize Spinner and Array Adapter behaviour" is very popular. So I present my point of view how this problem can be solved. There some key features have been implemented:
  1. Layout for ListItem and DropDownList is distinct from default one. There are only drawables.
  2. Supplying Promt in Spinner via different layout. It also solves initial item selection problem of Spinner.
  3. Styling items in the DropDown List.
  
Copyrights:
I have used some graphical resources and there are links to sources:
[App Icon (CC0 Public Domain)](https://pixabay.com/ru/%D1%84%D1%83%D1%82%D0%B1%D0%BE%D0%BB-%D1%88%D0%B0%D1%80-%D1%8F%D0%B9%D1%86%D0%BE-%D0%B0%D0%BC%D0%B5%D1%80%D0%B8%D0%BA%D0%B0%D0%BD%D1%81%D0%BA%D0%B8%D0%B9-%D1%84%D1%83%D1%82%D0%B1%D0%BE%D0%BB-297151/)

Logos of NFL teams(Fair Use - because it is an educational project to discover some approaches for solving learning task):
  1. [Buffalo Bills Logo. By Source, Fair use](https://en.wikipedia.org/w/index.php?curid=18272265)
  2. [Miami Dolphins logo. By Source, Fair use](https://en.wikipedia.org/w/index.php?curid=40154833)
  3. [New England Patriots logo. By Source, Fair use](https://en.wikipedia.org/w/index.php?curid=18256701)
  4. [New York Jets logo. By Source, Fair use](https://en.wikipedia.org/w/index.php?curid=18321623)
  5. [Baltimore Ravens logo. By Source, Fair use](https://en.wikipedia.org/w/index.php?curid=18269151)
  6. [Cincinnati Bengals logo. By Cincinnati Bengals](http://www.sportslogos.net/logo.php?id=403), [Public Domain](https://commons.wikimedia.org/w/index.php?curid=21291400)
  7. [Cleveland Browns logo. By Source (WP:NFCC#4), Fair use](https://en.wikipedia.org/w/index.php?curid=50803428)
  8. [Pittsburgh Steelers logo. By Brighterorange](http://www.steelers.com), [Public Domain](https://commons.wikimedia.org/w/index.php?curid=21186064)
  9. [Houston Texans logo. By Source, Fair use](https://en.wikipedia.org/w/index.php?curid=18319979)
  10. [Indianapolis Colts logo. By Baltimore Colts](http://www.sportslogos.net/logo.php?id=593), [Public Domain](https://commons.wikimedia.org/w/index.php?curid=21367556)
  11. [Jacksonville Jaguars logo. By Source, Fair use](https://en.wikipedia.org/w/index.php?curid=51867879)
  12. [Tennessee Titans logo. By Source, Fair use](https://en.wikipedia.org/w/index.php?curid=18257036)
  13. [Denver Broncos logo. By Source, Fair use](https://en.wikipedia.org/w/index.php?curid=18258229)
  14. [Kansas City Chiefs logo. By Source, Fair use](https://en.wikipedia.org/w/index.php?curid=18320276)
  15. [Los Angeles Chargers logo. By Source, Fair use](https://en.wikipedia.org/w/index.php?curid=18272780)
  16. [Oakland Raiders logo. By Source, Fair use](https://en.wikipedia.org/w/index.php?curid=22374939)

Wordmarks:
  1. [Buffalo Bills wordmark. By Buffalo Bills - NFLCommunications.com, Public Domain](https://commons.wikimedia.org/w/index.php?curid=52096444)
  2. [Miami Dolphins wordmark. By Miami Dolphins - NFLCommunications.com, Public Domain](https://commons.wikimedia.org/w/index.php?curid=52095937)
  3. [New England Patriots wordmark. By New England Patriots](https://commons.wikimedia.org/w/index.php?curid=42792314) - This is the version of the wordmark without the "Flying Elvis" head logo normally incorporated at the bottom, as seen at the top of http://www.patriots.com. SVG created using the Trace Bitmap tool in Inkscape., Public Domain
  4. [New York Jets wordmark. By New York Jets - NFLCommunications.com, Public Domain](https://commons.wikimedia.org/w/index.php?curid=52095616)
  5. [Baltimore Ravens wordmark. By Moe Epsilon](http://www.sportslogos.net/logo.php?id=323), [Public Domain](https://commons.wikimedia.org/w/index.php?curid=21062583)
  6. [Cincinnati Bengals wordmark. By Cincinnati Bengals](https://commons.wikimedia.org/w/index.php?curid=52188471) - NFLCommunications.com, Public Domain
  7. [Cleveland Browns wordmark. By Drawn by User:LBDesigns](http://prod.static.browns.clubs.nfl.com/assets/img/2014/BrownsLogo.png), [Public Domain](https://commons.wikimedia.org/w/index.php?curid=50156125)
  8. [Pittsburgh Steelers wordmark. By The original uploader was Kalel2007 at English Wikipedia - Transferred from en.wikipedia to Commons., Public Domain](https://commons.wikimedia.org/w/index.php?curid=42559793)
  9. [Houston Texans wordmark. By Houston Texans](http://www.sportslogos.net/logo.php?id=574), [Public Domain](https://commons.wikimedia.org/w/index.php?curid=21366387)
  10. [Indianapolis Colts wordmark. By Indianapolis Colts](http://www.sportslogos.net/logo.php?id=594), [Public Domain](https://commons.wikimedia.org/w/index.php?curid=21367554)
  11. [Jacksonville Jaguars wordmark. By Jacksonville Jaguars - NFLCommunications.com, Public Domain](https://commons.wikimedia.org/w/index.php?curid=52064487)
  12. [Tennessee Titans wordmark. By Tennessee Titans](http://www.sportslogos.net/logos/view/16026531999/Tennessee_Titans/1999/Alternate_Logo), [Public Domain](https://commons.wikimedia.org/w/index.php?curid=42612199)
  13. [Denver Broncos wordmark. By Denver Broncos - NFLCommunications.com, Public Domain](https://commons.wikimedia.org/w/index.php?curid=52082578)
  14. [Kansas City Chiefs wordmark. By Kansas City Chiefs - NFLCommunications.com, Public Domain](https://commons.wikimedia.org/w/index.php?curid=52043550)
  15. [Los Angeles Chargers wordmark. By San Diego/Los Angeles Chargers - File:San Diego Chargers wordmark.svg, with the city named removed., Public Domain](https://commons.wikimedia.org/w/index.php?curid=54992867)
  16. [Oakland Raiders wordmark. By Oakland Raiders - NFLCommunications.com, Public Domain](https://commons.wikimedia.org/w/index.php?curid=52082687)
