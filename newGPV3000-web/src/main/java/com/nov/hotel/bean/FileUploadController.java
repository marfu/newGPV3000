/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.bean;

import com.nov.hotel.entities.EtatChambreEnum;
import com.nov.hotel.entities.TCategorieChambre;
import com.nov.hotel.entities.TChambre;
import com.nov.hotel.entities.TOffreTarifaire;
import com.nov.hotel.entities.TTarif;
import com.nov.hotel.services.TCategorieChambreService;
import com.nov.hotel.services.TChambreService;
import com.nov.hotel.services.TOffreTarifaireService;
import com.nov.hotel.services.TTarifService;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "fileUploadController")
public class FileUploadController {

    private final String destination = "/home/manukey/Bureau/file/";

    @EJB
    private TChambreService tchambreService;

    @EJB
    private TCategorieChambreService tcategorieChambreService;

    @EJB
    private TOffreTarifaireService toffreService;

    @EJB
    private TTarifService ttarifService;

    private final Date today = new Date();

    public String generate(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyz1234567890"; // Tu supprimes les lettres dont tu ne veux pas
        String pass = "";
        for (int x = 0; x < length; x++) {
            int i = (int) Math.floor(Math.random() * 36); // Si tu supprimes des lettres tu diminues ce nb
            pass += chars.charAt(i);
        }

        return pass;
    }

    public int gen() {
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(30000));
    }

    private static String generateStorngPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 1000;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private static String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    public void writePassword(String msg) {
        String dest = "/home/manukey/Bureau/password/";
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        String fileName = "password" + fmt.format(new Date()) + ".txt";
        File f = new File(dest + fileName);
        try {

            PrintWriter fich;
            int n = 5;

            fich = new PrintWriter(new BufferedWriter(new FileWriter(f, true))); //true c'est elle qui permet d'écrire à la suite des donnée enregistrer et non de les remplacé 

            fich.println(msg);

            fich.println("\n");
            fich.close();

        } catch (IOException exception) {
            System.out.println("Erreur lors de la lecture : " + exception.getMessage());
        }
//       
    }

    public String copyFile(String fileName, InputStream in) {
        String file = null;
        try {
            // write the inputStream to a FileOutputStream

            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");

            fileName = fileName.replaceAll("\\s+", "");
            fileName = fileName + fmt.format(new Date());

            try (OutputStream out = new FileOutputStream(new File(destination + fileName))) {
                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }

                in.close();
                out.flush();
            }
            file = destination + fileName;
            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return file;
    }

    /**
     * Mise a jour circonscription electorale
     *
     * @return String return success
     */
//     
    public String uploadFCategorieChambre(FileUploadEvent event) throws NoSuchAlgorithmException, InvalidKeySpecException {
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            String file = copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                TCategorieChambre tcategorieChambre = new TCategorieChambre();
                // use comma as separator
                String[] results = line.split(cvsSplitBy);

                String libelleCat = results[0]; //get first cell
                String descriptCat = results[1];

                tcategorieChambre = tcategorieChambreService.finbyCategorieChambreByLib(libelleCat);

                if (tcategorieChambre == null) {
                    tcategorieChambre = new TCategorieChambre();
                    tcategorieChambre.setCatChambreLib(libelleCat);
                    tcategorieChambre.setCatChambreDesc(descriptCat);
                    tcategorieChambre.setCatChambreDateCrea(today);
//                    tcategorieChambre.setCliStatut();
                    tcategorieChambre = tcategorieChambreService.CreerOrUpdateTCategorieChambre(tcategorieChambre);

                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "success";
    }

    public String uploadFChambre(FileUploadEvent event) throws NoSuchAlgorithmException, InvalidKeySpecException {
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            String file = copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                TChambre tchambre = new TChambre();
                // use comma as separator
                String[] results = line.split(cvsSplitBy);
                String numChambre = results[0];//get first cell
                String nomChambre = results[1]; 

                String libelleCat = results[2];

                TCategorieChambre tcategorieChambre = tcategorieChambreService.finbyCategorieChambreByLib(libelleCat);

                if (tcategorieChambre == null) {

                } else {
                    tchambre = tchambreService.findChambrebyLib(nomChambre);
                    if (tchambre == null) {
                        tchambre = new TChambre();
                        tchambre.setChCategorie(tcategorieChambre);
                        tchambre.setChDateCreate(today);
                        tchambre.setChLib(nomChambre);
                        tchambre.setChNumeroChambre(numChambre);
                        tchambre.setEtat(EtatChambreEnum.LIBRE);
                        tchambre = tchambreService.CreerOrUpdate(tchambre);

                    }
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "success";
    }

    public String uploadFOffreTarifaire(FileUploadEvent event) throws NoSuchAlgorithmException, InvalidKeySpecException {
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            String file = copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                //TChambre tchambre = new TChambre();
                // use comma as separator
                String[] results = line.split(cvsSplitBy);

                String libelleOffre = results[0]; //get first cell

                String libelleCat = results[1];
                String prix = results[2];
                double prixs;

                TCategorieChambre tcategorieChambre = tcategorieChambreService.finbyCategorieChambreByLib(libelleCat);

                if (tcategorieChambre == null) {

                } else {
                    TOffreTarifaire toffreTarifaire = new TOffreTarifaire();
                    toffreTarifaire = toffreService.findOffreTarifaireByLib(libelleOffre);
                    if (toffreTarifaire == null) {
                        toffreTarifaire = new TOffreTarifaire();
                        toffreTarifaire.setOffDateCreate(today);
                        toffreTarifaire.setOffTitre(libelleOffre);
                        toffreTarifaire = toffreService.CreerOrUpdateTOffreTarifaire(toffreTarifaire);

                        TTarif ttarif = new TTarif();
                        ttarif = ttarifService.findOffreTarifaire(toffreTarifaire.getOffreId(), tcategorieChambre.getCatChambreId());
                        if (ttarif == null) {
                            ttarif = new TTarif();
                            ttarif.setChCategorie(tcategorieChambre);
                            ttarif.setOffre(toffreTarifaire);
                            prixs = Double.parseDouble(prix);
                            ttarif.setTTARIF_PRIX(prixs);
                            ttarif = ttarifService.CreerOrUpdate(ttarif);
                        }
                    } else {

                        TTarif ttarif = new TTarif();
                        ttarif = ttarifService.findOffreTarifaire(toffreTarifaire.getOffreId(), tcategorieChambre.getCatChambreId());
                        if (ttarif == null) {
                            ttarif = new TTarif();
                            ttarif.setChCategorie(tcategorieChambre);
                            ttarif.setOffre(toffreTarifaire);
                            prixs = Double.parseDouble(prix);
                            ttarif.setTTARIF_PRIX(prixs);
                            ttarif = ttarifService.CreerOrUpdate(ttarif);
                        }
                    }

                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return "success";
    }

}
