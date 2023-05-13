Feature: As a user i can see the trial price in my local currency

#  @happy-path
  Scenario Outline: User can see trial price in his country currency
    Given I am a non-registered customer
    And I navigate to "https://subscribe.jawwy.tv/"
    When I select my country "<countryCode>"
    Then I can see trial price displayed in my "<currency>"

    Examples:
      | countryCode  | currency     |
      | eg           | جنيه مصري    |
      | ae           | درهم إماراتي |
      | jo           | دينار أردني  |
      | iq           | دينار عراقي  |
      | om           | ريال عماني   |
      | tn           | دينار تونسي  |

  @happy-path
  Scenario Outline: User get a free trial when selecting any plan for the first time
    Given I am a non-registered customer
    And I navigate to "https://subscribe.jawwy.tv/"
    When I select my country "<countryCode>"
    And I select plan "<planName>"
    Then I get a free trial

    Examples:
      | countryCode  | planName |
      | tn           | لايت      |
      | tn           | الأساسية  |
      | tn           | بريميوم  |
      | eg           | لايت      |
      | eg           | الأساسية  |
      | eg           | بريميوم  |
      | ae           | لايت      |
      | ae           | الأساسية  |
      | ae           | بريميوم  |
      | dz           | لايت      |
      | dz           | الأساسية  |
      | dz           | بريميوم  |
      | dj           | لايت      |
      | dj           | الأساسية  |
      | dj           | بريميوم  |
      | td           | لايت      |
      | td           | الأساسية  |
      | td           | بريميوم  |
      | iq           | لايت      |
      | iq           | الأساسية  |
      | iq           | بريميوم  |
      | ma           | لايت      |
      | ma           | الأساسية  |
      | ma           | بريميوم  |
      | ye           | لايت      |
      | ye           | الأساسية  |
      | ye           | بريميوم  |
      | ps           | لايت      |
      | ps           | الأساسية  |
      | ps           | بريميوم  |