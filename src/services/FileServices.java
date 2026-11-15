package services;

import model.Pokemon;

import javax.xml.stream.*;
import java.io.*;
import java.lang.classfile.constantpool.PoolEntry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FileServices {
    public void Serializar (List<Pokemon> pokemons, String ruta) {
        try (FileOutputStream fichero = new FileOutputStream(ruta);
             ObjectOutputStream objeto = new ObjectOutputStream(fichero)) {

            objeto.writeObject(pokemons);
            System.out.println("Fichero serializado en " + ruta);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Pokemon> Deseralizar (String ruta) {
        File fichero = new File(ruta);
        List<Pokemon> pokemons = new ArrayList<>();

        try (FileInputStream entrada = new FileInputStream(fichero);
             ObjectInputStream salida = new ObjectInputStream(entrada)) {

            pokemons = (List<Pokemon>) salida.readObject();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return pokemons;
    }

    public void toXML (List<Pokemon> pokemons, String ruta) {
        XMLOutputFactory fabrica = XMLOutputFactory.newInstance();
        SimpleDateFormat simple = new SimpleDateFormat("dd-MM-yyyy");
        try {
            FileWriter fichero = new FileWriter(ruta);
            XMLStreamWriter escritor = fabrica.createXMLStreamWriter(fichero);

            escritor.writeStartDocument("UTF-8","1.0");
            escritor.writeCharacters("\n");
            escritor.writeStartElement("Pokemons");
            escritor.writeCharacters("\n");

            for (Pokemon p: pokemons) {
                escritor.writeStartElement("Pokemon");
                escritor.writeAttribute("id", String.valueOf(p.getId()));
                escritor.writeCharacters("\n");
                escritor.writeStartElement("Nome");
                escritor.writeCharacters(p.getNome() != null ? p.getNome() : "");
                escritor.writeEndElement();
                escritor.writeCharacters("\n");

                escritor.writeStartElement("Nacemento");
                String fecha = (p.getNacemento() != null) ? simple.format(p.getNacemento()) : "";
                escritor.writeCharacters(fecha);
                escritor.writeEndElement();
                escritor.writeCharacters("\n");

                escritor.writeStartElement("PokedexEntry");
                escritor.writeCharacters(String.valueOf(p.getPokedex()));
                escritor.writeEndElement();
                escritor.writeCharacters("\n");

                escritor.writeCharacters("Adestrador");
                escritor.writeCharacters(String.valueOf(p.getAdestrador()));
                escritor.writeEndElement();
                escritor.writeCharacters("\n");

                escritor.writeEndElement();
            }
            escritor.writeEndElement();
            escritor.writeEndDocument();

            escritor.flush();
            escritor.close();
            fichero.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Pokemon> Importar (String ruta) {
        List<Pokemon> lista = new ArrayList<>();
        XMLInputFactory fabrica = XMLInputFactory.newInstance();

        try (FileReader fichero = new FileReader(ruta)){
            XMLStreamReader lectura = fabrica.createXMLStreamReader(fichero);
            Pokemon pokemon = null;
            StringBuilder texto = new StringBuilder();

            while (lectura.hasNext()) {
                int valor = lectura.next();

                switch (valor) {
                    case XMLStreamConstants.START_ELEMENT:
                        texto.setLength(0);
                        if ("Pokemon".equals(lectura.getLocalName())) {
                            String escrita = lectura.getAttributeValue(null, "id");
                            if (escrita != null) {
                                pokemon = new Pokemon();
                                pokemon.setId(Long.parseLong(escrita));
                            }
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        texto.append(lectura.getText());
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        String etiqueta = lectura.getLocalName();
                        if (pokemon != null) {
                            if ("nome".equals(etiqueta)) {
                                pokemon.setNome(texto.toString().trim());
                            }
                            else if ("nacemento".equals(etiqueta)) {
                                String fecha = texto.toString().trim();

                                if (!fecha.isEmpty() && ! "null".equals(fecha)) {
                                    pokemon.setNacemento(Fecha(fecha));
                                }
                            }
                            else if ("Adestrador".equals(etiqueta)) {
                                lista.add(pokemon);
                                pokemon = null;
                            }
                        }
                        break;
                }
            }
            lectura.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    public Date Fecha (String fechas) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(fechas);
        } catch (Exception e) {
            try {
                return new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyy", Locale.ENGLISH).parse(fechas);
            }
            catch (Exception h) {
                return new Date();
            }
        }
    }
}
