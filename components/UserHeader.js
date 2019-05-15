import React from 'react'
import { StyleSheet, Text, View, TouchableOpacity, Image, ImageBackground } from 'react-native'

export default class UserHeader extends React.Component {
  constructor(props) {
    super(props)
    this.state = {width: 10}
  }
  handlePress = () => {
    console.log('Header Pressed')
  }
  measureUserAvatar = (event) => {
    const width = event.nativeEvent.layout.width
    this.setState({'width': width})
  }
  render() {
    const {avatarURL, userName, userSub, style} = this.props
    const {width} = this.state
    return (
      <View style={style}>
        <TouchableOpacity
          style = {styles.button}
          onPress = {this.handlePress}
          underlayColor = '#fff'>
          <ImageBackground
            source = {require('../assets/header_background.png')}
            style = {styles.container}
            imageStyle = {{borderRadius: 20}}>
            <Image
              style = {{...styles.userAvatar, borderRadius: width/2}}
              source = {{'uri': avatarURL}}
              onLayout = {(event) => this.measureUserAvatar(event)} />
            <View style = {styles.subContainer}>
              <Text style = {styles.userName} numberOfLines={1}> {userName} </Text>
              <Text style = {styles.userSub} numberOfLines={1}> {userSub} </Text>
            </View>
          </ImageBackground>
        </TouchableOpacity>
      </View>
    )
  }
}

const styles = StyleSheet.create({
  button: {
    width: '100%',
    height: '100%'
  },
  container: {
    flex: 1,
    flexDirection: 'row',
    alignItems: 'center'
  },
  userAvatar: {
    width: '20%',
    aspectRatio: 1.0,
    marginLeft: '2%',
  },
  subContainer: {
    flex: 1,
    flexDirection: 'column',
    width: '80%',
  },
  userName: {
    fontSize: 28,
    color: 'white',
    fontWeight: '600',
    paddingRight: '2%'
  },
  userSub: {
    fontSize: 16,
    color: 'white',
    marginLeft: 2,
    paddingRight: '2%'
  }
})
