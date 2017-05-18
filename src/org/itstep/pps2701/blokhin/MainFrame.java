package org.itstep.pps2701.blokhin;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Vit on 18.05.2017.
 */
public class MainFrame  extends JFrame {
    private CompositeApplication compositeApp;

    private JScrollPane treeScroller;
    private JPanel controlPanel;
    private JTree tree;

    private JButton addCountryBtn, addRegionBtn, addCityBtn;
    private JButton removeCountryBtn, removeRegionBtn, removeCityBtn;

    public MainFrame(String title) throws HeadlessException {
        super(title);
        compositeApp = new CompositeApplication();

        buildGUI();
    }

    private void buildGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,500);
        setLocationRelativeTo(null);
        setResizable(false);

        tree = new JTree(createTreeModel());
        treeScroller = new JScrollPane();

        controlPanel = new JPanel();
        controlPanel.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.weightx = 1;
        cons.gridx = 0;

        addCountryBtn = new JButton("Добавить страну");
        addRegionBtn = new JButton("Добавить регион");
        addCityBtn = new JButton("Добавить город");

        removeCountryBtn = new JButton("Удалить страну");
        removeRegionBtn = new JButton("Удалить регион");
        removeCityBtn = new JButton("Удалить город");

        setBtnActions();

        controlPanel.add(addCountryBtn, cons);
        controlPanel.add(addRegionBtn, cons);
        controlPanel.add(addCityBtn, cons);
        controlPanel.add(removeCountryBtn, cons);
        controlPanel.add(removeRegionBtn, cons);
        controlPanel.add(removeCityBtn, cons);

        getContentPane().add(treeScroller);
        getContentPane().add(controlPanel, BorderLayout.EAST);

        setVisible(true);
    }

    private void setBtnActions() {
        addCountryBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newCountryName = JOptionPane.showInputDialog("Введите название страны:");
                if(newCountryName != "") compositeApp.createCountry(newCountryName);

                tree = new JTree(createTreeModel());
                tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

                getContentPane().remove(treeScroller);
                treeScroller = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                getContentPane().add(treeScroller);

                getContentPane().revalidate();
                getContentPane().repaint();
            }
        });

        addRegionBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // здесь и в аналогичных методах:
                // для добавления региона требуется в списке выделить страну, куда будем его добавлять
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent(); // получаем выделенный элемент
                if(node == null) return;                                                                    // проверка
                if(node.getUserObject().getClass().equals(Country.class)) {                                 // является ли выделенный элемент экземпляром Country
                    String newRegionName = JOptionPane.showInputDialog("Введите название региона:");
                    if(newRegionName != "") {
                        compositeApp.createRegion(newRegionName, (Country) node.getUserObject());

                        tree = new JTree(createTreeModel());
                        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

                        getContentPane().remove(treeScroller);
                        treeScroller = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                        getContentPane().add(treeScroller);

                        getContentPane().revalidate();
                        getContentPane().repaint();
                    } else return;
                }
            }
        });
        addCityBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if(node == null) return;
                if(node.getUserObject().getClass().equals(Region.class)) {

                    String newCityName = JOptionPane.showInputDialog("Введите название города:");
                    if(newCityName != "") {
                        compositeApp.createCity(newCityName, (Region) node.getUserObject());

                        tree = new JTree(createTreeModel());
                        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

                        getContentPane().remove(treeScroller);
                        treeScroller = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                        getContentPane().add(treeScroller);

                        getContentPane().revalidate();
                        getContentPane().repaint();
                    } else return;
                }
            }
        });

        removeCountryBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if(node == null) return;
                if(node.getUserObject().getClass().equals(Country.class)) {
                    compositeApp.removeCountry((Country) node.getUserObject());

                    tree = new JTree(createTreeModel());
                    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

                    getContentPane().remove(treeScroller);
                    treeScroller = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                    getContentPane().add(treeScroller);

                    getContentPane().revalidate();
                    getContentPane().repaint();
                }
            }
        });

        removeRegionBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                DefaultMutableTreeNode nodeParent = (DefaultMutableTreeNode)node.getParent();
                if(node == null) return;
                if(node.getUserObject().getClass().equals(Region.class)) {
                    compositeApp.removeRegion((Region) node.getUserObject(), (Country) nodeParent.getUserObject());

                    tree = new JTree(createTreeModel());
                    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

                    getContentPane().remove(treeScroller);
                    treeScroller = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                    getContentPane().add(treeScroller);

                    getContentPane().revalidate();
                    getContentPane().repaint();
                }
            }
        });

        removeCityBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                DefaultMutableTreeNode nodeParent = (DefaultMutableTreeNode)node.getParent();
                if(node == null) return;
                if(node.getUserObject().getClass().equals(City.class)) {
                    compositeApp.removeCity((City) node.getUserObject(), (Region) nodeParent.getUserObject());

                    tree = new JTree(createTreeModel());
                    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

                    getContentPane().remove(treeScroller);
                    treeScroller = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

                    getContentPane().add(treeScroller);

                    getContentPane().revalidate();
                    getContentPane().repaint();
                }
            }
        });
    }


    // создание модели дерева
    private TreeModel createTreeModel() {

        // корень дерева
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Административные единицы");

        // рекурсивное добавление административных единиц в дерево
        for(Country country : compositeApp.getCountries()) {
            DefaultMutableTreeNode countryNode = new DefaultMutableTreeNode(country);
            for(UnitComponent region : country.getRegions()) {
                DefaultMutableTreeNode regionNode = new DefaultMutableTreeNode(region);
                for(UnitComponent city : ((Region) region).getCities()) {
                    regionNode.add(new DefaultMutableTreeNode(city));
                }
                countryNode.add(regionNode);
            }
            root.add(countryNode);
        }

        // создаем стандартную модель
        return new DefaultTreeModel(root);
    } // createTreeModel

} // class MainFrame
