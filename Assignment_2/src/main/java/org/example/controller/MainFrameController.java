package org.example.controller;

import org.example.Client;
import org.example.Coada;
import org.example.Timer;
import org.example.singlePointAccess.GUIFrameSinglePointAccess;
import org.example.views.MainFrameView;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainFrameController {
    static MainFrameView mainFrameView;
    private static final HashMap<Integer, ArrayList<Client>> clientiInAsteptare = new HashMap<>(); /// panel ul de jos

    private static final HashMap<Integer, Coada> casa = new HashMap<>(); ///cozile efective

    public void startLogic(int noOfClients, int noOfQueues, int tSimulationMax, int tArrivalMin, int tArrivalMax, int tServiceMin, int tServiceMax )
    {
        mainFrameView = new MainFrameView();
        GUIFrameSinglePointAccess.changePanel(mainFrameView.getMainPanel(),"Simulare cozi");


        randomClientsGenerator(noOfClients,tArrivalMin,tArrivalMax,tServiceMin,tServiceMax,mainFrameView.getCoadaClientiPanel());

        for(int i = 0 ; i< noOfQueues ;i++)
        {
            Coada coada = new Coada(tSimulationMax);
            casa.put(i + 1, coada);
            mainFrameView.getListaCoziPanel().add(coada);
        }
        mainFrameView.getListaCoziPanel().revalidate();
        mainFrameView.getListaCoziPanel().repaint();

        Timer timer = new Timer(tSimulationMax);
        timer.startTimer(mainFrameView.getSecundeLabel());

    }

    public void randomClientsGenerator(Integer n, Integer tArrivalMin, Integer tArrivalMax, Integer tServiceMin, Integer tServiceMax, JPanel clientPanel)
    {   int arrival = 0;
        int service = 0;
        for(int i = 0 ; i < n; i++) {
            arrival = (int)Math.floor(Math.random()* (tArrivalMax - tArrivalMin + 1) + tArrivalMin);
            service = (int)Math.floor(Math.random()* (tServiceMax - tServiceMin + 1) + tServiceMin);
//            System.out.println("LOG_test1: SERVICE = " + service + "\n");
            Client client = new Client(i+1,arrival,service);
            clientPanel.add(client);

            if(clientiInAsteptare.containsKey(arrival)) ///daca avem deja clienti cu acelasi arrivalTime, adaugam clientul nou in lista
            {
                clientiInAsteptare.get(arrival).add(client);
            }
            else ///altfel creem pozitia in hashMap si lista de clienti
            {
                ArrayList<Client> aux = new ArrayList<>();
                aux.add(client);
                clientiInAsteptare.put(arrival,aux);
            }
        }
    }

    public static ArrayList<Client> preluareClienti (Integer tCurrent)
    {
        ArrayList<Client> aux = new ArrayList<>();
        aux = clientiInAsteptare.get(tCurrent);
        clientiInAsteptare.remove(tCurrent);
        if(aux == null) return null;
        for(Client client : aux)
        {
            mainFrameView.getCoadaClientiPanel().remove(client);
            mainFrameView.getCoadaClientiPanel().revalidate();
            mainFrameView.getCoadaClientiPanel().repaint();
        }
        return aux;
    }

    public static void distribuireClienti(Integer tCurrent)
    {
        ArrayList<Client> aux = new ArrayList<>();
        aux = preluareClienti(tCurrent);
        if(aux == null) return; /// daca nu avem clienti
        for(Client client : aux)
        {
            strategieDeDistribuire(client);
        }
    }

    public static void strategieDeDistribuire(Client client)
    {
        if(client == null) return;
        int minTime = Integer.MAX_VALUE;
        Coada casaAux = null;
        for(Coada casaCurenta : casa.values())
        {
            if(casaCurenta.getTimpAsteptareTotal() == 0)
            {
                casaAux = casaCurenta;
                break;
            }
            if(casaCurenta.getTimpAsteptareTotal() < minTime)
            {
                minTime = casaCurenta.getTimpAsteptareTotal();
                casaAux = casaCurenta;
            }
        }

        casaAux.addClient(client);

    }

    public static boolean nuMaiAvemClienti()
    {
        for(Coada coada: casa.values())
        {
            if(!coada.isEmpty()) return false;
        }
        if(clientiInAsteptare.isEmpty()) return true;
        return false;
    }

        public static String clientiInAsteaptareLOG()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Waiting clients: ");
        for(ArrayList<Client> clients : clientiInAsteptare.values())
        {
            for(Client client : clients)
            {
                sb.append("(" + client.getId()+","+client.gettArrival()+","+client.gettService()+")");
                sb.append(";  ");
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

        public static String clentiDinCoziLOG()
    {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Integer, Coada> entry : casa.entrySet())
        {
            Integer key = entry.getKey();
            Coada value = entry.getValue();
            if(key.equals(1)) sb.append("\nQueue " + key + ":");
            else sb.append("Queue " + key + ":");
            if(value.isEmpty()) sb.append("Closed");
            else
            {
                for(Client client : value.getCasa())
                {
                    sb.append("(" + client.getId()+","+client.gettArrival()+","+client.gettService()+")");
                    sb.append(";");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public HashMap<Integer, ArrayList<Client>> getClientiInAsteptare() {
        return clientiInAsteptare;
    }
}
