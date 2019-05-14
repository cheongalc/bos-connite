import {createStackNavigator, createAppContainer} from 'react-navigation'
import HomePage from './screens/HomePage'
import AccountInfoPage from './screens/AccountInfoPage'
import QuestionnairePage from './screens/QuestionnairePage'

const MainNavigator = createStackNavigator(
  {
    HomePage: {screen: HomePage},
    AccountInfoPage: {screen: AccountInfoPage},
    QuestionnairePage: {screen: QuestionnairePage}
  },
  {
    initialRouteName: 'HomePage',
    headerMode: 'none',
    navigationOptions: {
      headerVisible: false
    }
  }
)

const App = createAppContainer(MainNavigator)

export default App
