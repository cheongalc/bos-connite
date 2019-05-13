import {createStackNavigator, createAppContainer} from 'react-navigation'
import HomePage from './screens/HomePage'
import AccountInfoPage from './screens/AccountInfoPage'

const MainNavigator = createStackNavigator(
  {
    HomePage: {screen: HomePage},
    AccountInfoPage: {screen: AccountInfoPage}
  },
  { initialRouteName: 'HomePage' }
)

const App = createAppContainer(MainNavigator)

export default App
