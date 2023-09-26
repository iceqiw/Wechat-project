import { useState, useRef, useEffect } from "react";
import { View } from "@tarojs/components";
import CustomHeader from "@/components/header";
import { AtButton, AtTextarea } from "taro-ui";
import "./scroll.scss";

const AutoScrollComponent = () => {
    const [text, setText] = useState("");
    const [textLines, setTextLines] = useState([]);
    const scrollContainerRef = useRef(null);
    const [isScrolling, setIsScrolling] = useState(false);
    const [currentIndex, setCurrentIndex] = useState(0);

    useEffect(() => {
        const lines = text.split("\n").filter((line) => line.trim() !== "");
        setTextLines(lines);

        // Scroll the container to the bottom when new content is added
        if (scrollContainerRef.current) {
            scrollContainerRef.current.scrollTop = 0;
            setCurrentIndex(0);
        }
    }, [text]);

    useEffect(() => {
        let autoScrollInterval;
        console.info("running");
        if (isScrolling) {
            // Automatically scroll down every 1 second
            autoScrollInterval = setInterval(() => {
                if (scrollContainerRef.current) {
                    const element = document.querySelector(
                        `div[class="scroll-item active"]`
                    ); // Replace "your-key" with the desired key or identifier

                    if (element) {
                        const height = element.offsetHeight;
                        console.log(`Element height: ${height}px`);
                        scrollContainerRef.current.scrollTop += height;
                    }

                    //setCurrentIndex((prevIndex) => prevIndex+1);
                    setCurrentIndex((prevIndex) => {
                        var ind = (prevIndex + 1) % textLines.length;
                        console.info(ind);
                        if (ind == 0) {
                            scrollContainerRef.current.scrollTop = 0;
                        }
                        return ind;
                    });
                }
            }, 2000); // 1000ms = 1 second
        } else {
            clearInterval(autoScrollInterval);
        }

        return () => {
            clearInterval(autoScrollInterval);
        };
    }, [isScrolling, textLines]);

    const handleTextChange = (value) => {
        setText(value);
    };

    const handleStartScrolling = () => {
        setIsScrolling(true);
    };

    const handleStopScrolling = () => {
        setIsScrolling(false);
    };

    return (
        <View>
            <CustomHeader title="Show Word" />

            <View className="at-col">
                {!isScrolling && (
                    <AtTextarea
                        count={false}
                        className="input-area"
                        value={text}
                        onChange={handleTextChange}
                        maxLength={200}
                        placeholder="你的问题是..."
                    />
                )}
            </View>
            <View className="at-row">
                <AtButton
                    type="primary"
                    onClick={handleStartScrolling}
                    disabled={isScrolling}
                >
                    Start Scrolling
                </AtButton>
                <AtButton onClick={handleStopScrolling} disabled={!isScrolling}>
                    Stop Scrolling
                </AtButton>
            </View>
            <View className="at-row">
                <div className="scroll-container" ref={scrollContainerRef}>
                    {textLines.map((line, index) => (
                        <div
                            key={index}
                            className={`scroll-item ${
                                index === currentIndex ? "active" : ""
                            }`}
                        >
                            {line}
                        </div>
                    ))}
                </div>
            </View>
        </View>
    );
};

export default AutoScrollComponent;
