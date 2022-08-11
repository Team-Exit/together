import React, { useEffect, useRef, useState } from "react";

function NaverMap() {
    const [markers, setMarkers] = useState<naver.maps.Marker[]>();
    const mapRef = useRef<naver.maps.Map>();

    useEffect(() => {
        const initMap = () => {
            mapRef.current = new naver.maps.Map("map", {
                center: new naver.maps.LatLng(37.511337, 127.012084), //현재 접속한 사용자 위치 알아낼 수 있음?
                zoom: 13,
                zoomControl: true,
                tileTransition: true, // 지도 타일 전환 시 페이드 인 효과
                // scaleControl: false, // 우측하단 축척 단위가 계속 중복이 되면 지우셈.
                // scrollWheel: false, // 마우스 스크롤 사용 여부,
                overlayZoomEffect: null, // 도형, 마커 등 오버레이 줌 효과 적용 <-> 'all',
                zoomControlOptions: {
                    position: naver.maps.Position.RIGHT_TOP,
                }
            });

            setMarkers([new naver.maps.Marker({
                position: new naver.maps.LatLng(37.4979517, 127.0276188),
                map: mapRef.current,
                animation: 2
            })])
        };

        initMap();
        mapRef.current.addListener('init', () => {
            window.addEventListener('resize', () => {
                const mapWidth = window.innerWidth;
                const mapHeight = window.innerHeight;
                const fixedSize = new naver.maps.Size(mapWidth, mapHeight);
                mapRef.current?.setSize(fixedSize);
            });
        })
        naver.maps.Event.addListener(mapRef.current, 'dblclick', (e: any) => {
            setMarkers((prev: naver.maps.Marker[]) => {
                return [...prev, new naver.maps.Marker({
                    position: new naver.maps.LatLng(e?.latlng._lat, e?.latlng._lng),
                    map: mapRef.current,
                    animation: 2
                })]
            })
        })
    }, []);

    useEffect(() => {
        const markerClickEvent = (marker: naver.maps.Marker) => {
            naver.maps.Event.addListener(marker, 'click', (e: any) => {
                mapRef.current.panTo(e?.coord, {
                    duration: 500,
                    easing: "easeOutCubic"
                });
            })
        };
        if (Array.isArray(markers)) {
            for (let key of markers) {
                markerClickEvent(key);
            }
        }
    }, [markers])

    const mapStyle = {
        width: window.innerWidth,
        height: window.innerHeight
    };

    return (
        <React.Fragment>
            <div id="map" style={mapStyle}></div>
        </React.Fragment>
    );
}

export default NaverMap;