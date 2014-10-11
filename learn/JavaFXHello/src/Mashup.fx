import javafx.ui.*; 

var stockSymbol = TextField { };
var zipCodeField = TextField { };

Frame {
    title: 'Mashup Application'
    visible: true

    content: SplitPane {
        orientation: HORIZONTAL
        content:
            [
            SplitView {
                weight: 0.5
                content:
                BorderPanel {
                    top: GridBagPanel {
                        border: CompoundBorder {
                            borders:
                                [
                                TitledBorder {
                                    title: 'Input Areas'
                                },
                                EmptyBorder {
                                    top: 5
                                    left: 5
                                    bottom: 5
                                    right: 5
                                }
                                ]
                            }
                        cells:
                            [
                            GridCell {
                                anchor: EAST
                                gridx: 0
                                gridy: 0
                                content: SimpleLabel {
                                    text: 'Stock: '
                                }
                            },
                            GridCell {
                                anchor: WEST
                                fill: HORIZONTAL
                                weightx: 1
                                gridx: 1
                                gridy: 0
                                content: stockSymbol
                            },
                            GridCell {
                                anchor: EAST
                                gridx: 0
                                gridy: 1
                                insets: {top: 2}
                                content: SimpleLabel {
                                    text: 'Zipcode: '
                                }
                            },
                            GridCell {
                                gridx: 1
                                gridy: 1
                                fill: HORIZONTAL
                                weightx: 1
                                insets: {top: 2}
                                content: zipCodeField
                            },
                            GridCell {
                                anchor: WEST
                                weightx: 1.0
                                gridx: 0
                                gridy: 2
                                gridwidth: 2
                                fill: HORIZONTAL
                                content: SimpleLabel {
                                    border: EmptyBorder {
                                        top: 10
                                    }
                                    text: "Input Some characters,Press Enter"
                                }
                            }
                        ]//end cells
                    }//end BorderPanel

                    center: BorderPanel {
                        border: CompoundBorder {
                            borders:
                                [
                                    TitledBorder {
                                        title: "Display Stock Price:"
                                    },
                                    EmptyBorder {
                                        top: 5
                                        left: 5
                                        bottom: 5
                                        right: 5
                                    }
                                ]
                            }
                            center: TextArea {
                                font: Font { face: SERIF style: ITALIC size: 16 }
                                lineWrap: true
                                wrapStyleWord: true
                                editable: false
                                text: ''
                            }
                        }                         
                    }
                },//SplitView
                SplitView {
                    weight: 0.5
                    content: SplitPane {
                    border: CompoundBorder {
                        borders:
                            [TitledBorder {
                                title: "Display Mashup Results:"
                            },
                            EmptyBorder {
                                top: 5
                                left: 5
                                bottom: 5
                                right: 5
                            }]
                        }
                        orientation: VERTICAL
                        content:
                        [SplitView {
                            weight: 0.5
                            content:EditorPane {
                                opaque: true
                                preferredSize: {height: 250 width: 250}
                                contentType: HTML
                                editable: false
                                text: ''
                            }
                        },
                        SplitView {
                            weight: 0.5
                            content: EditorPane {
                                preferredSize: {height: 250 width: 250}
                                editable: true
                                text: ''
                        }
                    }]
                }
            }
        ]
    }//end SplitPane
}// end frame