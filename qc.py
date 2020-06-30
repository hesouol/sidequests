


disponiveis = ["ANATOLIAN", "ART PUZZLE", "BLUEBIRD", "CASTORLAND", "CLEMENTONI", "COBBLE HILL", "DINO", "D-TOYS", "EDUCA", "EUROGRAPHICS", "GIBSONS", "GOLD PUZZLE", "GRAFIKA", "HEYE", "IMPRONTE EDIZIONI", "JUMBO", "KS GAMES", "MASTER PIECES", "NORIS", "PIATNIK", "POMEGRANATE", "RAVENSBURGER", "SCHMIDT", "STEP PUZZLE", "SUNSOUT", "TREFL", "WHITE MOUNTAIN", "WREBBIT"]

texto = """
EURO

  Ravensbuger –– High quality thick and sturdy packaging and puzzle pieces.  Standard grid type puzzle. Wide range of images.

  Jumbo –– High quality thick and sturdy packaging and puzzle pieces.  Standard grid type puzzle. Publishes JVH and Wasgij.

  Falcon — High quality thick and sturdy packaging and puzzle pieces.  Standard grid type puzzle. Tends to cozy UK images.

  Heye — Good quality boxes. Standard grid type puzzle with a wide variety of shapes. Best cartoon puzzles.

Piatnik — Good quality with thick pieces and a snug fit. Standard grid cut. Fine art, cartoon, and photo collage puzzles.

  Schmidt — High quality packaging and puzzle pieces.  Quirky grid cut with lots of variety. Excellent fantasy puzzles.

  Clementoni — Solid quality with thick pieces and a linen finish. Standard grid cut. Conventional images.

  Anatolian — Solid quality with thick pieces and nice boxes. Standard grid cut. Conventional licensed images.

  D-Toys (editions with “wide variety of shapes” sticker) –– Standard grid cut puzzle with six piece shapes. Offers unique cartoon, folk art (“classic tales”), and fine art puzzles.

UK

  Gibson — Lovely boxes, thick pieces with a waxy feel. Overall stellar quality. Grid cut pieces. Pieces can appear to fit where they do not.

  House of Puzzles —  Best unusual piece cut and images created for puzzling with lots of colors and textures.

  WHSmith — Similar to Gibson, good boxes and sturdy puzzle pieces in a grid cut.  Pieces can appear to fit where they do not.

  Otter House – Nice smaller boxes, good overall quality on the pieces. Hard to get outside UK.

CANADIAN

  Cobble Hill —  ‘Linen finish’ boxes and puzzle pieces have a nice feel. Pieces are thick. Random cut.

  Eurographics — Pieces are thick. Not quite grid cut with a wide variety of piece shapes. Large image selection. Some puzzle dust and glare.

U.S.

  Bits and Pieces — Thick pieces with a non-grid cut.  Best shaped puzzles in the industry.

  Buffalo Games — Standard grid cut. Quality sturdy boxes and puzzle pieces, Wysocki, Josephine Wall, Aimee Stewart and more

  eeboo — Standard grid cut. Quality sturdy boxes and puzzle pieces, modern folksy round tables images and more

  Lafayette Puzzle Company — Grid cut puzzle with a seamless fit, sturdy and attractive boxes and pieces.

  Pomegranate — Fine art puzzles with excellent quality.

  Sunsout—  Unattractive boxes but good puzzles. Thick pieces with a non-grid cut. Tight fit. Tons of images.

  Springbok — Thick puzzle pieces, a super tight fit, unusual piece shapes and good colors. Images are so-so. Lots of photo puzzles.

 Vermont Christmas Company — Fairly thick puzzle pieces, a semi-random cut with a good variety of shapes, overall nice quality and lots of unique Christmas and Halloween images.

  White Mountain — Great image selection and regular releases. Larger pieces with a nice random cut. Pieces are on the thin side, with occasional image lift and some puzzle dust.

Japan/Korea

Puzzlelife — Puzzles come with a large poster and glue and are of good quality. Most reasonably priced of the Asian puzzle brands.

  Yanoman — Thick pieces with a grid cut similar to Ravensburger. Excellent overall quality.

"""

TEXTO = texto.upper()

for x in disponiveis:
    c = TEXTO.count(x)
    if c>0 :
        print(f"{x}: {c}")
